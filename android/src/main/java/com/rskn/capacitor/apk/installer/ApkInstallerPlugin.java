package com.rskn.capacitor.apk.installer;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.content.FileProvider;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
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

    private static final int REQUEST_CODE_MANAGE_UNKNOWN_APP_SOURCES = 1001;

    @PluginMethod
    public void checkInstallPermission(PluginCall call) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean canInstallApps = getContext().getPackageManager().canRequestPackageInstalls();
            JSObject result = new JSObject();
            result.put("canInstall", canInstallApps);
            call.resolve(result);
        } else {
            // For older Android versions, assume the permission is granted
            JSObject result = new JSObject();
            result.put("canInstall", true);
            call.resolve(result);
        }
    }

    @PluginMethod
    public void requestInstallPermission(PluginCall call) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
            intent.setData(Uri.parse("package:" + getContext().getPackageName()));
            startActivityForResult(call, intent, "handleInstallPermissionResult");
        } else {
            // Permission not required for Android versions below 8.0
            JSObject result = new JSObject();
            result.put("canInstall", true);
            call.resolve(result);
        }
    }

    @ActivityCallback
    private void handleInstallPermissionResult(PluginCall call, Intent data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean canInstallApps = getContext().getPackageManager().canRequestPackageInstalls();
            JSObject result = new JSObject();
            result.put("canInstall", canInstallApps);
            call.resolve(result);
        } else {
            // Should never reach here for Android < 8.0
            JSObject result = new JSObject();
            result.put("canInstall", true);
            call.resolve(result);
        }
    }
}
