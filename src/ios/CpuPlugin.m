#import "CpuPlugin.h"
#include <sys/sysctl.h>
#include <mach/machine.h>
#include <mach/mach_host.h> // Include for host_info


@implementation CpuPlugin

- (void)getCpuInfo:(CDVInvokedUrlCommand *)command {
    CDVPluginResult* pluginResult = nil;
    NSDictionary* cpuInfo = [self collectCpuInfo];

    if (cpuInfo) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:cpuInfo];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (NSDictionary *)collectCpuInfo {
    NSMutableDictionary *info = [NSMutableDictionary dictionary];

    // CPU architecture
    [info setObject:[self cpuArchitecture] forKey:@"cpuArchitecture"];

    // CPU cores
    [info setObject:@([self cpuCoreCount]) forKey:@"cpuCores"];

    [info setObject:[self currentABI] forKey:@"primaryABI"];
    // Other details can be added here

    return info;
}

- (NSString *)currentABI {
    #if defined(__arm64__) || defined(__aarch64__)
    return @"arm64";
    #elif defined(__arm__)
    return @"arm";
    #elif defined(__x86_64__)
    return @"x86_64";
    #elif defined(__i386__)
    return @"i386";
    #else
    return @"Unknown";
    #endif
}

- (NSString *)cpuArchitecture {
    host_basic_info_data_t hostInfo;
    mach_msg_type_number_t infoCount = HOST_BASIC_INFO_COUNT;
    host_info(mach_host_self(), HOST_BASIC_INFO, (host_info_t)&hostInfo, &infoCount);

    switch (hostInfo.cpu_type) {
        case CPU_TYPE_ARM:
            return @"ARM";
        case CPU_TYPE_ARM64:
            return @"ARM64";
        case CPU_TYPE_X86:
            return @"x86";
        case CPU_TYPE_X86_64:
            return @"x86_64";
        default:
            return @"Unknown";
    }
}

- (NSUInteger)cpuCoreCount {
    return [NSProcessInfo processInfo].activeProcessorCount;
}

@end
