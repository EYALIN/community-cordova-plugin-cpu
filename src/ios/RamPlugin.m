#import "RamPlugin.h"
#import <mach/task.h> // Import the mach/task.h module
#import <mach/mach.h> // Import the mach/mach.h module
#import <Cordova/CDVAvailability.h>

@implementation RamPlugin

- (void)pluginInitialize {
}


- (void)getRAMInfo:(CDVInvokedUrlCommand *)command {
 CDVPluginResult* pluginResult = nil;
     @try {
         // Get total RAM
         NSProcessInfo *processInfo = [NSProcessInfo processInfo];
         long long totalRAM = [processInfo physicalMemory];

         // Get used RAM
         struct mach_task_basic_info info;
         mach_msg_type_number_t infoCount = MACH_TASK_BASIC_INFO_COUNT;
         kern_return_t kerr = task_info(mach_task_self(), MACH_TASK_BASIC_INFO, (task_info_t)&info, &infoCount);
         if (kerr == KERN_SUCCESS) {
             long long usedRAM = info.resident_size;

             // Calculate free RAM
             long long freeRAM = totalRAM - usedRAM;

           // Calculate RAM usage percentage
                      double ramUsagePercent = (double)usedRAM / totalRAM * 100.0;

                      // Format ramUsagePercent with two decimal places
                      NSNumberFormatter *numberFormatter = [[NSNumberFormatter alloc] init];
                      [numberFormatter setNumberStyle:NSNumberFormatterDecimalStyle];
                      [numberFormatter setMaximumFractionDigits:2];
                      NSString *formattedRamUsagePercent = [numberFormatter stringFromNumber:@(ramUsagePercent)];


             NSDictionary *ramInfo = @{
                 @"totalRAM": @(totalRAM),
                 @"usedRAM": @(usedRAM),
                 @"freeRAM": @(freeRAM),
                 @"ramUsagePercent": formattedRamUsagePercent
             };

             pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:ramInfo];
         } else {
             pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Failed to get RAM information"];
         }
     } @catch (NSException *exception) {
         pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[exception reason]];
     }

     [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
 }

 @end





