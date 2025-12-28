import { WebPlugin } from '@capacitor/core';
export class ApkInstallerWeb extends WebPlugin {
    checkInstallPermission() {
        return Promise.resolve({ canInstall: false });
    }
    requestInstallPermission() {
        return Promise.resolve({ canInstall: false });
    }
    installApk(_options) {
        return Promise.resolve();
    }
}
//# sourceMappingURL=web.js.map