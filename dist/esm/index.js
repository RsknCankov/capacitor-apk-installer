import { registerPlugin } from '@capacitor/core';
const ApkInstaller = registerPlugin('ApkInstaller', {
    web: () => import('./web').then((m) => new m.ApkInstallerWeb()),
});
export * from './definitions';
export { ApkInstaller };
//# sourceMappingURL=index.js.map