/**
 * Represents the RAM information response.
 */
export interface IRAMInfoResponse {
    /**
     * The total physical RAM available on the device in bytes.
     */
    totalRAM: number;

    /**
     * The amount of RAM that is currently in use in bytes.
     */
    usedRAM: number;

    /**
     * The amount of free RAM available for use in bytes.
     */
    freeRAM: number;

    /**
     * The percentage of RAM usage, calculated as (usedRAM / totalRAM) * 100.
     */
    ramUsagePercent: number;
}

export default class RamManager {
    getRAMInfo(): Promise<IRAMInfoResponse>;
}
