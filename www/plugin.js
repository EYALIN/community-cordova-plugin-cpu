var PLUGIN_NAME = 'RamPlugin';

var RamPlugin = {
    getRAMInfo: function(phrase) {
        return new Promise(function (resolve, reject) {
            cordova.exec(resolve, reject, PLUGIN_NAME, 'getRAMInfo', [phrase]);
        });
    },
};

module.exports = RamPlugin;
