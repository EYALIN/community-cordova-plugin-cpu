package wifiplugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.apache.cordova.CordovaInterface;

public class PingTask {

    public static void ping(String address, int count, int timeout, CallbackContext callbackContext,CordovaInterface cordova) {
           cordova.getThreadPool().execute(() -> {
                       try {
                           String command = "/system/bin/ping -c " + count + " -W " + timeout + " " + address;
                           Process process = Runtime.getRuntime().exec(command);

                           BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                           List<String> pingResults = new ArrayList<>();
                           String line;
                           while ((line = reader.readLine()) != null) {
                               pingResults.add(line);
                           }

                           process.waitFor();
                           JSONArray jsonArray = new JSONArray(pingResults);
                           PluginResult result = new PluginResult(PluginResult.Status.OK, jsonArray);
                           callbackContext.sendPluginResult(result);
                       } catch (Exception e) {
                           callbackContext.error("Error during ping: " + e.getMessage());
                       }
                   });
       }
}
