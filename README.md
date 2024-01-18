# Key Received Demo

An Android app demo using Secure API Keys.

This is the result of the course [Secure API Keys with a Public-Key Cryptography on Android](https://www.udemy.com/course/secure-api-keys-with-a-public-key-cryptography-on-android/)

Success | Loading | Error&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
:-------------------------:|:-------------------------:|:-------------------------:
![](https://github.com/paulohc/key-received-demo/assets/18506267/2aabe488-58e6-4684-b9e1-4a219e1e879e) | ![](https://github.com/paulohc/key-received-demo/assets/18506267/b3c75c35-3226-4b3f-82f6-59e8b784cabd) | ![](https://github.com/paulohc/key-received-demo/assets/18506267/2f664ee3-319d-44b0-90e9-7e19a049aee9)

## Getting Started

Use Android Studio to run this project, so the app will fetch the encoded keys from the server an show it.
You also can fetch the keys from a [local sever](https://github.com/paulohc/key-provider-demo), just remember to update the `BASE_URL` (located in `KeyProviderModule.kt` file) to point to localhost.

```
// private const val BASE_URL = "https://key-provider-demo.onrender.com"
private const val BASE_URL = "http://10.0.2.2:8080"
```
