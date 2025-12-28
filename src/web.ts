import {WebPlugin} from '@capacitor/core';

import type {ApkInstallerPlugin} from './definitions';

export class ApkInstallerWeb extends WebPlugin implements ApkInstallerPlugin {
    checkInstallPermission(): Promise<{ canInstall: boolean; }> {
        return Promise.resolve({canInstall: false});
    }

    requestInstallPermission(): Promise<{ canInstall: boolean; }> {
        return Promise.resolve({canInstall: false});
    }
    installApk(_options: { filePath: string; }): Promise<void> {
        return Promise.resolve();
    }
}
