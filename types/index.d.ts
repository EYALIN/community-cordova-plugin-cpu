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
    cpuModel: number;

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

export default class CpuManager {
    getCpuInfo(): Promise<ICpu>;
}
