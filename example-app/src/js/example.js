import { ApkInstaller } from 'capacitor-apk-installer';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    ApkInstaller.echo({ value: inputValue })
}
