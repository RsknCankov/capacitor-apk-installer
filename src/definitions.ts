export interface ApkInstallerPlugin {
  installApk(options: { filePath: string }): Promise<void>;
}
