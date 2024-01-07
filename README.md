[![NPM version](https://img.shields.io/npm/v/community-cordova-plugin-cpu)](https://www.npmjs.com/package/community-cordova-plugin-cpu)


# community-cordova-plugin-cpu README

I dedicate a considerable amount of my free time to developing and maintaining many cordova plugins for the community ([See the list with all my maintained plugins][community_plugins]).
To help ensure this plugin is kept updated,
new features are added and bugfixes are implemented quickly,
please donate a couple of dollars (or a little more if you can stretch) as this will help me to afford to dedicate time to its maintenance.
Please consider donating if you're using this plugin in an app that makes you money,
or if you're asking for new features or priority bug fixes. Thank you!

[![](https://img.shields.io/static/v1?label=Sponsor%20Me&style=for-the-badge&message=%E2%9D%A4&logo=GitHub&color=%23fe8e86)](https://github.com/sponsors/eyalin)


---

# Community Cordova Plugin CPU

## Overview
This Cordova plugin provides a way to access basic CPU information of the mobile device. It supports both Android and iOS platforms, offering different sets of information based on the platform due to their respective system limitations and capabilities.

## Installation
To install the plugin in your Cordova project, use the following command:
```
cordova plugin add community-cordova-plugin-cpu
```

## Usage
To use the plugin, call the `getCpuInfo` method. This method is asynchronous and returns a Promise that resolves with the CPU information.

```javascript
document.addEventListener('deviceready', onDeviceReady, false);

function onDeviceReady() {
    CpuManager.getCpuInfo().then(function(info) {
        console.log('CPU Information:', info);
    }).catch(function(error) {
        console.error('Error getting CPU information:', error);
    });
}
```

### API

#### getCpuInfo()
Returns a Promise that resolves with an object containing CPU information. The structure of the returned object varies between Android and iOS:

#### Android
The response object includes:

- `cpuArchitecture`: The architecture of the CPU (e.g., ARMv7, ARMv8).
- `cpuCores`: The number of CPU cores available on the device.
- `cpuFrequencyMax`: The maximum CPU frequency (in MHz).
- `cpuFrequencyMin`: The minimum CPU frequency (in MHz).
- `cpuModel`: The model or identifier of the CPU.
- `primaryABI`: The primary Application Binary Interface (ABI) of the device.
- `secondaryABI`: The secondary ABI of the device, if available.
- `cpuFrequencyInfo`: An array containing information about each CPU core, including core index and current frequency.

#### iOS
Due to iOS restrictions, the response object includes a limited set of information:

- `cpuArchitecture`: The architecture of the CPU (e.g., ARM64, x86_64).
- `cpuCores`: The number of active CPU cores.
- `primaryABI`: The primary ABI based on the CPU architecture.

**Note**: iOS does not allow access to certain details like CPU frequency or the exact CPU model.

## Platform Specifics
- **Android**: Provides comprehensive CPU information, including architecture, core count, frequency, model, and ABIs.
- **iOS**: Limited to providing CPU architecture, core count, and primary ABI due to iOS system restrictions.

## Contributing
Contributions to the plugin are welcome. Please ensure to follow the coding standards and submit your pull requests for review.

## License
This project is licensed under the MIT License.

---
[community_plugins]: https://github.com/EYALIN?tab=repositories&q=community&type=&language=&sort=
