package ramplugin; // Replace with your package name

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Debug;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class RamPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("getRAMInfo".equals(action)) {
            try {
                           ActivityManager activityManager = (ActivityManager) cordova.getActivity().getSystemService(Context.ACTIVITY_SERVICE);
                           ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                           activityManager.getMemoryInfo(memoryInfo);

                           JSONObject ramInfo = new JSONObject();
                           ramInfo.put("totalRAM", memoryInfo.totalMem);
                           ramInfo.put("freeRAM", memoryInfo.availMem);
                           ramInfo.put("usedRAM", memoryInfo.totalMem - memoryInfo.availMem);
                            // Calculate RAM usage percentage with two decimal places
                           double ramUsagePercent = ((double)(memoryInfo.totalMem - memoryInfo.availMem) / memoryInfo.totalMem) * 100.0;
                           ramInfo.put("ramUsagePercent", Math.round(ramUsagePercent * 100.0) / 100.0);

                           callbackContext.success(ramInfo);
                       } catch (Exception e) {
                           callbackContext.error("Error retrieving RAM information: " + e.getMessage());
                       }

                       return true;
        }
        return false;
    }

    private JSONObject getRAMInfo() throws JSONException {
        Context context = this.cordova.getActivity().getApplicationContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        JSONObject ramInfo = new JSONObject();
        int apiVersion = Build.VERSION.SDK_INT;

        // Total RAM
        if (apiVersion >= Build.VERSION_CODES.JELLY_BEAN) {
            long totalMemory = activityManager.getMemoryClass() * 1024 * 1024; // in bytes
            ramInfo.put("totalMemory", totalMemory);
        }

        // Free RAM
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        long freeMemory = memoryInfo.getTotalPss() * 1024; // in bytes
        ramInfo.put("freeMemory", freeMemory);





        return ramInfo;
    }

    private String encodeBitmapAsBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}
