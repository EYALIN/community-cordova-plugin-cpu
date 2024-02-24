#import <Cordova/CDV.h>

@interface CpuPlugin : CDVPlugin

- (void)getCpuInfo:(CDVInvokedUrlCommand*)command;

@end
