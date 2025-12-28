export interface ApkInstallerPlugin {
  installApk(options: { filePath: string }): Promise<void>;

  checkInstallPermission(): Promise<{ canInstall: boolean }>;

  requestInstallPermission(): Promise<{ canInstall: boolean }>;
}
