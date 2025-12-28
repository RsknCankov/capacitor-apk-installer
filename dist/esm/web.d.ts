import { WebPlugin } from '@capacitor/core';
import type { ApkInstallerPlugin } from './definitions';
export declare class ApkInstallerWeb extends WebPlugin implements ApkInstallerPlugin {
    checkInstallPermission(): Promise<{
        canInstall: boolean;
    }>;
    requestInstallPermission(): Promise<{
        canInstall: boolean;
    }>;
    installApk(_options: {
        filePath: string;
    }): Promise<void>;
}
