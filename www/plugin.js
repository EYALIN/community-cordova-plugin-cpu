var PLUGIN_NAME = 'CpuPlugin';

var CpuPlugin = {
    getCpuInfo: function(phrase) {
        return new Promise(function (resolve, reject) {
            cordova.exec(resolve, reject, PLUGIN_NAME, 'getCpuInfo', [phrase]);
        });
    },

    /**
     * Get CPU usage percentage
     * @returns {Promise<Object>} CPU usage information
     */
    getCpuUsage: function() {
        return new Promise(function (resolve, reject) {
            cordova.exec(resolve, reject, PLUGIN_NAME, 'getCpuUsage', []);
        });
    },

    /**
     * Get CPU temperature (if available)
     * @returns {Promise<Object>} CPU temperature information
     */
    getCpuTemperature: function() {
        return new Promise(function (resolve, reject) {
            cordova.exec(resolve, reject, PLUGIN_NAME, 'getCpuTemperature', []);
        });
    },

    /**
     * Get comprehensive performance metrics
     * @returns {Promise<Object>} Performance metrics including CPU, memory pressure, etc.
     */
    getPerformanceMetrics: function() {
        return new Promise(function (resolve, reject) {
            cordova.exec(resolve, reject, PLUGIN_NAME, 'getPerformanceMetrics', []);
        });
    }
};

module.exports = CpuPlugin;
