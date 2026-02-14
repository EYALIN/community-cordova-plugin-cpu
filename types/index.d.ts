export interface ICpu {
    // CPU architecture, e.g., ARMv7, ARMv8, x86, x86_64, etc.
    cpuArchitecture: string;

    // Number of CPU cores available on the device
    cpuCores: number;

    // Maximum CPU frequency in MHz
    cpuFrequencyMax: string;

    // Minimum CPU frequency in MHz
    cpuFrequencyMin: string;

    // CPU model or identifier
    cpuModel: string;

    // Primary ABI (Application Binary Interface)
    primaryABI: string;

    // Secondary ABI (Application Binary Interface) if available
    secondaryABI: string;

    // Array of CPU core-specific information
    cpuFrequencyInfo: ICpuCores[];
}

// Interface for CPU core-specific information
export interface ICpuCores {
    // Core index or identifier
    coreIndex: number;
    // Current CPU frequency for the core in MHz
    currentFrequency: string;
}

// Interface for CPU usage information
export interface ICpuUsage {
    // Overall CPU usage percentage (0-100)
    overallUsage: number;
    // Per-core CPU usage percentages
    perCoreUsage: number[];
    // Number of active processes
    processCount: number;
    // Load average (1 minute)
    loadAverage1: number;
    // Load average (5 minutes)
    loadAverage5: number;
    // Load average (15 minutes)
    loadAverage15: number;
}

// Interface for CPU temperature information
export interface ICpuTemperature {
    // CPU temperature in Celsius
    temperature: number;
    // Temperature status (normal, warm, hot, critical)
    status: string;
    // Whether temperature data is available
    available: boolean;
}

// Interface for comprehensive performance metrics
export interface IPerformanceMetrics {
    // CPU information
    cpu: ICpu;
    // CPU usage
    cpuUsage: ICpuUsage;
    // CPU temperature
    cpuTemperature: ICpuTemperature;
    // Memory pressure (0-100)
    memoryPressure: number;
    // Performance score (0-1000)
    performanceScore: number;
}

export default class CpuManager {
    getCpuInfo(): Promise<ICpu>;
    getCpuUsage(): Promise<ICpuUsage>;
    getCpuTemperature(): Promise<ICpuTemperature>;
    getPerformanceMetrics(): Promise<IPerformanceMetrics>;
}
