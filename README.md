[![NPM version](https://img.shields.io/npm/v/community-cordova-plugin-cpu)](https://www.npmjs.com/package/community-cordova-plugin-cpu)


# community-cordova-plugin-cpu README

I dedicate a considerable amount of my free time to developing and maintaining many cordova plugins for the community ([See the list with all my maintained plugins][community_plugins]).
To help ensure this plugin is kept updated,
new features are added and bugfixes are implemented quickly,
please donate a couple of dollars (or a little more if you can stretch) as this will help me to afford to dedicate time to its maintenance.
Please consider donating if you're using this plugin in an app that makes you money,
or if you're asking for new features or priority bug fixes. Thank you!

[![](https://img.shields.io/static/v1?label=Sponsor%20Me&style=for-the-badge&message=%E2%9D%A4&logo=GitHub&color=%23fe8e86)](https://github.com/sponsors/eyalin)



## Overview

The `CpuManager` is a TypeScript class designed to provide information about the CPU (Central Processing Unit) of a device. This README file aims to provide a detailed description of the code and its usage.

## Installation

To use the `CpuManager` class, you need to install it as a dependency in your project:
```bash
cordova plugin add community-cordova-plugin-cpu
```
* for now it's Android only


## Table of Contents

- [Introduction](#introduction)
- [Installation](#installation)
- [Usage](#usage)
- [API Reference](#api-reference)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The `CpuManager` class exports methods to retrieve essential information about the device's CPU, such as architecture, core count, frequency, model, and more. It uses TypeScript and is intended for use in Node.js or other JavaScript environments.


## Usage

To utilize the `CpuManager` class in your project, follow these steps:

1. Import the `CpuManager` class into your TypeScript/JavaScript file:

```typescript
import CpuManager from 'community-cordova-plugin-cpu';
```

2. Create an instance of the `CpuManager` class:

```typescript
declare var CpuPlugin: CpuManager;
```

3. Use the `getCpuInfo` method to retrieve CPU information asynchronously:

```javascript
cpuManager.getCpuInfo()
  .then((cpuInfo) => {
    console.log('CPU Information:', cpuInfo);
  })
  .catch((error) => {
    console.error('Error fetching CPU information:', error);
  });
```
or
```javascript
var cpuObj = await CpuPlugin.getCpuInfo();
```

## API Reference

### `CpuManager`

#### `getCpuInfo(): Promise<ICpu>`

Retrieves detailed information about the device's CPU.

- Returns: A Promise that resolves to an `ICpu` object containing the following properties:

    - `cpuArchitecture`: A string representing the CPU architecture (e.g., ARMv7, ARMv8, x86, x86_64, etc.).
    - `cpuCores`: The number of CPU cores available on the device.
    - `cpuFrequencyMax`: The maximum CPU frequency in MHz.
    - `cpuFrequencyMin`: The minimum CPU frequency in MHz.
    - `cpuModel`: The CPU model or identifier (number).
    - `primaryABI`: The primary ABI (Application Binary Interface) used by the CPU.
    - `secondaryABI`: The secondary ABI (Application Binary Interface) if available.
    - `cpuFrequencyInfo`: An array of objects containing CPU core-specific information, each with the following properties:
        - `coreIndex`: The core index or identifier (number).
        - `currentFrequency`: The current CPU frequency for the core in MHz.

### Interfaces

#### `ICpu`

An interface describing the CPU information structure:

- `cpuArchitecture` (string): CPU architecture, e.g., ARMv7, ARMv8, x86, x86_64, etc.
- `cpuCores` (number): Number of CPU cores available on the device.
- `cpuFrequencyMax` (string): Maximum CPU frequency in MHz.
- `cpuFrequencyMin` (string): Minimum CPU frequency in MHz.
- `cpuModel` (number): CPU model or identifier.
- `primaryABI` (string): Primary ABI (Application Binary Interface).
- `secondaryABI` (string): Secondary ABI (Application Binary Interface) if available.
- `cpuFrequencyInfo` (ICpuCores[]): Array of CPU core-specific information.

#### `ICpuCores`

An interface describing CPU core-specific information:

- `coreIndex` (number): Core index or identifier.
- `currentFrequency` (string): Current CPU frequency for the core in MHz.

## Contributing

Contributions to this project are welcome. If you encounter issues, have suggestions, or want to contribute improvements, please feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

Feel free to use the `CpuManager` class in your projects to retrieve and utilize CPU information effectively. If you have any questions or need assistance, please refer to the [Issues](https://github.com/EYALIN/community-cordova-plugin-cpu/issues) section of the GitHub repository for this project.

[community_plugins]: https://github.com/EYALIN?tab=repositories&q=community&type=&language=&sort=
