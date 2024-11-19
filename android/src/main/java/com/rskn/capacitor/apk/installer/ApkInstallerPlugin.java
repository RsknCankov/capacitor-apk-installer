package com.rskn.capacitor.apk.installer;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.File;

@CapacitorPlugin(name = "ApkInstaller")
public class ApkInstallerPlugin extends Plugin {

    private final ApkInstaller implementation = new ApkInstaller();

    @PluginMethod
    public void installApk(PluginCall call) {
         String filePath = call.getString("filePath");
         if (filePath == null || filePath.isEmpty()) {
             call.reject("File path is required");
             return;
         }

         File apkFile = new File(filePath);
         if (!apkFile.exists()) {
             call.reject("File does not exist: " + filePath);
             return;
         }

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             if (!getContext().getPackageManager().canRequestPackageInstalls()) {
                 call.reject("Permission to install unknown apps is required.");
                 return;
             }
         }

         Uri apkUri = FileProvider.getUriForFile(
             getContext(),
             getContext().getPackageName() + ".fileprovider",
             apkFile
         );

         Intent intent = new Intent(Intent.ACTION_VIEW);
         intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
         intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

         try {
             getContext().startActivity(intent);
             call.resolve();
         } catch (Exception e) {
             call.reject("Failed to start installation: " + e.getMessage());
         }
     }
}
