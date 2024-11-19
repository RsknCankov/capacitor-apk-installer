import {WebPlugin} from '@capacitor/core';

import type {ApkInstallerPlugin} from './definitions';

export class ApkInstallerWeb extends WebPlugin implements ApkInstallerPlugin {
    installApk(_options: { filePath: string; }): Promise<void> {
        return Promise.resolve();
    }
}
