#import <Cordova/CDVPlugin.h>

@interface WifiPlugin : CDVPlugin {
}

// The hooks for our plugin commands
- (void)getRAMInfo:(CDVInvokedUrlCommand *)command;

@end
