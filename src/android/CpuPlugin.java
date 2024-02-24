package cpuplugin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.io.*;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CpuPlugin extends CordovaPlugin {
    private static final String TAG = "CpuPlugin";
    private PackageManager mPackageManager;

    /**
     * Delete externalCacheDirectory on app start
     *
     * @param cordova Cordova-instance
     * @param webView CordovaWebView-instance
     */
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    /**
     * Returns the application context.
     */
    private Context getContext() {
        return cordova.getActivity();
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        try {
            if (action.equals("getCpuInfo")) {
                JSONArray arrCpuCores = new JSONArray();
                mPackageManager = getContext().getPackageManager();
                String primaryABI = System.getProperty("os.arch");
                int numCores = Runtime.getRuntime().availableProcessors();
                String secondaryABI = System.getProperty("os.arch");
                StringBuilder cpuFrequencyInfo = new StringBuilder();

                for (int i = 0; i < numCores; i++) {
                    JSONObject objCore = new JSONObject();
                    objCore.put("coreIndex", i);
                    objCore.put("currentFrequency", getCPUCoreFrequency(i));
                    arrCpuCores.put(objCore);
                }

                JSONObject obj = new JSONObject();
                obj.put("cpuArchitecture", Build.CPU_ABI);
                obj.put("primaryABI", primaryABI);
                obj.put("secondaryABI", secondaryABI);
                obj.put("cpuModel", Build.HARDWARE);
                obj.put("cpuCores", Runtime.getRuntime().availableProcessors());
                obj.put("cpuFrequencyMax", getMaxCpuFrequency());
                obj.put("cpuFrequencyMin", getMinCpuFrequency());
                obj.put("cpuFrequencyInfo", arrCpuCores);

                String phrase = args.getString(0);
                final PluginResult result = new PluginResult(PluginResult.Status.OK, obj);
                callbackContext.sendPluginResult(result);
            }
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
            Log.d(TAG, e.getMessage());
            return false;
        }
        return true;
    }

    private String getCPUCoreFrequency(int coreIndex) {
        try {
            String cpuCorePath = "/sys/devices/system/cpu/cpu" + coreIndex + "/cpufreq/scaling_cur_freq";
            return convertKHzToMHz(readValueFromFile(cpuCorePath));
        } catch (IOException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private String getMaxCpuFrequency() {
        try {
            String cpuCorePath = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
            return convertKHzToMHz(readValueFromFile(cpuCorePath));
        } catch (IOException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private String getMinCpuFrequency() {
        try {
             String cpuCorePath = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";
             return convertKHzToMHz(readValueFromFile(cpuCorePath));
        } catch (IOException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private String readValueFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String value = reader.readLine();
        reader.close();
        return value;
    }

    private String convertKHzToMHz(String kHzValue) {
        try {
            long kHz = Long.parseLong(kHzValue);
            return String.valueOf(kHz / 1000) + " MHz";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "N/A";
        }
    }
}
