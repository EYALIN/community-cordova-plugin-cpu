interface WifiNetwork {
    SSID: string;
    BSSID: string;
    capabilities: string;
    frequency: string;
    level: number;
    security: string;
    channelWidth: string;
    distance: string;
    hasPassword: boolean;
}
interface NetworkDetails {
    type: string;
    state: string;
    isConnected: boolean;
    isConnectedToInternet?: boolean;
    canConnectToRouter?: boolean;
    isConnectedToWifi?: boolean;
}
interface ConnectedDeviceInfo {
    ipAddress: string;
    deviceName: string;
    localHost: boolean;
    loopbackAddress: boolean;
    hostAddress: string;
    canonicalHostName: string;
    multicastAddress: boolean;
    siteLocalAddress: boolean;
}

interface WifiDetails {
    isWifiEnabled: boolean;
    isSupportWifi: boolean;
    SSID: string;
    BSSID: string;
    IP: string;
    MAC: string;
    NetworkID: number;
    LinkSpeed: number;
    SignalStrength: number;
    Gateway: string;
    RSSI: number;
    Speed: number;
    Frequency: number;
    Channel: number;
    DNS1: string;
    DNS2: string;
}

interface PingResponse {
    line?: string;
    fullResponse?: string;
    progress?: number;
    status?: string;
    linesRead?: number;
}

interface IpInfo {
    type: string;
    signal: number;
    speed: number;
    ssid: string;
    internalip: string;
    macaddress: string;
    networkid: number;
    frequency: number;
    bssid: string;
    dns1: string;
    dns2: string;
    timezone: string;
    latitude?: number;
    longitude?: number;
    city?: string;
    street?: string;
    country?: string;
    region?: string;
    zipcode?: string;
    state?: string;
}
export default class WifiManager {
    getWifiList(): Promise<WifiNetwork[]>;
    getIpInfo(): Promise<IpInfo[]>;
    getAllWifiDetails(): Promise<WifiDetails>;
    isConnectedToInternet(): Promise<boolean>;
    canConnectToInternet(): Promise<number>;
    canConnectToRouter(): Promise<boolean>;
    connectToNetwork(ssid: string, password: string): Promise<void>;
    disconnectFromNetwork(): Promise<void>;
    isWifiEnabled(): Promise<boolean>;
    wifiToggle(): Promise<void>;
    checkAndRequestWifiPermission(): Promise<string>;
    getConnectedDevices(): Promise<ConnectedDeviceInfo[]>;
    getWifiStrength(): Promise<number>;
    getSignalStrength(): Promise<number>;
    ping(address: string, count: number, timeout: number, successCallback: (response: PingResponse) => void, errorCallback: (error: any) => void): void;

}
