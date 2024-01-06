package wifiplugin;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.DhcpInfo;

public class WifiDetailsUtils {

    public static void getAllWifiDetails(CordovaInterface cordova, CallbackContext callbackContext) {
 cordova.getThreadPool().execute(() -> {
           try {
               WifiManager wifiManager = (WifiManager) cordova.getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
               ConnectivityManager connectivityManager = (ConnectivityManager) cordova.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

               // Check if Wi-Fi is enabled
               boolean isWifiEnabled = wifiManager != null && wifiManager.isWifiEnabled();

               // Check if the device supports Wi-Fi
               boolean isSupportWifi = wifiManager != null;

               // Get Wi-Fi details
               WifiInfo wifiInfo = wifiManager.getConnectionInfo();
               JSONObject wifiDetails = new JSONObject();

               wifiDetails.put("isWifiEnabled", isWifiEnabled);
               wifiDetails.put("isSupportWifi", isSupportWifi);
               wifiDetails.put("SSID", wifiInfo.getSSID());
               wifiDetails.put("BSSID", wifiInfo.getBSSID());
               wifiDetails.put("IP", Formatter.formatIpAddress(wifiInfo.getIpAddress()));
               wifiDetails.put("MAC", wifiInfo.getMacAddress());
               wifiDetails.put("NetworkID", wifiInfo.getNetworkId());
               wifiDetails.put("LinkSpeed", wifiInfo.getLinkSpeed());
               wifiDetails.put("SignalStrength", wifiInfo.getRssi());

               // Additional information
               DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
               wifiDetails.put("Gateway", Formatter.formatIpAddress(dhcpInfo.gateway));
               wifiDetails.put("RSSI", wifiInfo.getRssi());
               wifiDetails.put("SignalStrength", wifiInfo.getRssi()); // Same as RSSI
               wifiDetails.put("Speed", wifiInfo.getLinkSpeed());
               wifiDetails.put("Frequency", wifiInfo.getFrequency());
               wifiDetails.put("Channel", getChannelFromFrequency(wifiInfo.getFrequency()));
               wifiDetails.put("DNS1", Formatter.formatIpAddress(dhcpInfo.dns1));
               wifiDetails.put("DNS2", Formatter.formatIpAddress(dhcpInfo.dns2));

               PluginResult result = new PluginResult(PluginResult.Status.OK, wifiDetails);
               callbackContext.sendPluginResult(result);
           } catch (Exception e) {
               callbackContext.error("Error getting Wi-Fi details: " + e.getMessage());
           }
       });
    }

       // Helper method to get the Wi-Fi channel from frequency
       private static int getChannelFromFrequency(int frequency) {
           if (frequency >= 2412 && frequency <= 2484) {
               return (frequency - 2412) / 5 + 1;
           } else if (frequency >= 5170 && frequency <= 5825) {
               return (frequency - 5170) / 5 + 34;
           }
           return -1; // Unknown channel
       }
}
