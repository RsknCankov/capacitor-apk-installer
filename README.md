# capacitor-apk-installer

This plugin is installing an already downloaded .apk file by given filePath

## Install

```bash
npm install capacitor-apk-installer
npx cap sync
```

## API

<docgen-index>

* [`installApk(...)`](#installapk)
* [`checkInstallPermission()`](#checkinstallpermission)
* [`requestInstallPermission()`](#requestinstallpermission)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### installApk(...)

```typescript
installApk(options: { filePath: string; }) => Promise<void>
```

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ filePath: string; }</code> |

--------------------


### checkInstallPermission()

```typescript
checkInstallPermission() => Promise<{ canInstall: boolean; }>
```

**Returns:** <code>Promise&lt;{ canInstall: boolean; }&gt;</code>

--------------------


### requestInstallPermission()

```typescript
requestInstallPermission() => Promise<{ canInstall: boolean; }>
```

**Returns:** <code>Promise&lt;{ canInstall: boolean; }&gt;</code>

--------------------

</docgen-api>
