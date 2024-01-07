package wifiplugin;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class IpInfoUtils {

    public static void getIpInfo(CordovaInterface cordova, CallbackContext callbackContext) {
        cordova.getThreadPool().execute(() -> {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) cordova.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                WifiManager wifiManager = (WifiManager) cordova.getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                LocationManager locationManager = (LocationManager) cordova.getActivity().getSystemService(Context.LOCATION_SERVICE);
                Geocoder geocoder = new Geocoder(cordova.getActivity(), Locale.getDefault());

                if (connectivityManager != null && wifiManager != null && locationManager != null) {
                    Network[] networks = connectivityManager.getAllNetworks();
                    JSONArray ipInfoArray = new JSONArray();
                    for (Network network : networks) {
                        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);

                        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            JSONObject ipInfoObject = new JSONObject();

                            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo(); // Get DHCP info for DNS details

                            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                            ipInfoObject.put("type", networkInfo.getTypeName());
                            ipInfoObject.put("signal", wifiInfo.getRssi());
                            ipInfoObject.put("speed", wifiInfo.getLinkSpeed());
                            ipInfoObject.put("ssid", wifiInfo.getSSID());
                            ipInfoObject.put("internalip", Formatter.formatIpAddress(wifiInfo.getIpAddress()));
                            ipInfoObject.put("macaddress", wifiInfo.getMacAddress());
                            ipInfoObject.put("networkid", wifiInfo.getNetworkId());
                            ipInfoObject.put("frequency", wifiInfo.getFrequency());
                            ipInfoObject.put("bssid", wifiInfo.getBSSID());
                            ipInfoObject.put("timezone", TimeZone.getDefault().getID());
                            ipInfoObject.put("dns1", Formatter.formatIpAddress(dhcpInfo.dns1)); // Add DNS1
                            ipInfoObject.put("dns2", Formatter.formatIpAddress(dhcpInfo.dns2)); // Add DNS2

                            if (location != null) {
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();
                                ipInfoObject.put("latitude", latitude);
                                ipInfoObject.put("longitude", longitude);

                                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                if (addresses != null && !addresses.isEmpty()) {
                                    Address address = addresses.get(0);
                                    ipInfoObject.put("city", address.getLocality());
                                    ipInfoObject.put("street", address.getThoroughfare());
                                    ipInfoObject.put("country", address.getCountryName());
                                    ipInfoObject.put("region", address.getSubAdminArea());
                                    ipInfoObject.put("zipcode", address.getPostalCode());
                                    ipInfoObject.put("state", address.getAdminArea());
                                }
                            }
                            ipInfoArray.put(ipInfoObject);
                        }
                    }

                    PluginResult result = new PluginResult(PluginResult.Status.OK, ipInfoArray);
                    callbackContext.sendPluginResult(result);
                } else {
                    callbackContext.error("Required managers are null");
                }
            } catch (Exception e) {
                callbackContext.error("Error getting IP information: " + e.getMessage());
            }
        });
    }
}
