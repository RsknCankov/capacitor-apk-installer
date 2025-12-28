var capacitorApkInstaller = (function (exports, core) {
    'use strict';

    const ApkInstaller = core.registerPlugin('ApkInstaller', {
        web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.ApkInstallerWeb()),
    });

    class ApkInstallerWeb extends core.WebPlugin {
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

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        ApkInstallerWeb: ApkInstallerWeb
    });

    exports.ApkInstaller = ApkInstaller;

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
