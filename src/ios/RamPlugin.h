#import <Cordova/CDVPlugin.h>

@interface RamPlugin : CDVPlugin {
}

// The hooks for our plugin commands
- (void)getRAMInfo:(CDVInvokedUrlCommand *)command;

@end
