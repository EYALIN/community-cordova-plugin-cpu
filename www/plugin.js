var PLUGIN_NAME = 'CpuPlugin';

var CpuPlugin = {
    getCpuInfo: function(phrase) {
        return new Promise(function (resolve, reject) {
            cordova.exec(resolve, reject, PLUGIN_NAME, 'getCpuInfo', [phrase]);
        });
    },
};

module.exports = CpuPlugin;
