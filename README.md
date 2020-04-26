<a href="https://user-images.githubusercontent.com/32475878/80305954-d5a36980-87dd-11ea-84c4-905a7abc881c.png"><img src="https://user-images.githubusercontent.com/32475878/80305954-d5a36980-87dd-11ea-84c4-905a7abc881c.png"></a>
</br>
[![Generic badge](https://img.shields.io/badge/release-v1.1.0-blue.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://shields.io/)
[![made-with-kotlin](https://img.shields.io/badge/Made%20with-kotlin-1f425f.svg)](https://github.com/JetBrains/kotlin)
[![Generic badge](https://img.shields.io/badge/MinimumSdk-16-Green.svg)](https://shields.io/)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/NitinPraksash9911/floating-loader-button/issues)
</br>

<a href="https://github.com/NitinPraksash9911/floating-loader-button">
<img align="right"  src="https://user-images.githubusercontent.com/32475878/80308271-a3990400-87eb-11ea-9d98-a1c8b86d438d.gif" width="200" height="410" /></a>

<p><h1 align="left">Floating Loader Button</h1></p>

<h4>Inbuilt circular progress loader in floating button</h4>
</br>

## Requirements

- Android Studio
- MinSdk 16

## Integration
Step.1 add jitpack to project level build.gradle

```groovy
   allprojects {
    repositories {
       ...
        maven { url 'https://jitpack.io' }
        
    }
}
```
Step.2 add dependency to module level build.gradle
```groovy
dependencies {
    implementation 'com.github.NitinPraksash9911:floating-loader-button:1.x.x'
}
```
### Usage

```xml
  <in.nitin.library.FloatingLoaderButton
        android:id="@+id/floatingLoaderBtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:loaderBackgroundColor="#50CCDF"
        app:loaderFabSize="Medium"
        app:loadingIconColor="#FFD600"
        app:loadingStatus="None" />
```
## Attrs

Name | Values 
--------- | --------
app:loaderBackgroundColor | to change the FloatingLoaderButton background color and by default it's color is black
app:loaderFabSize | to change the size of FloatingLoaderButton, there are three different size available 'Medium', 'Small' & 'Large' 
app:loadingIconColor | to change the arrow color and by default it's color is white
app:loadingStatus | use to define the state of loader it has three state  1 'None' for initial stage, 2 'Loading' to start circular loading, 3 'Finish' to stop circular loading (recomended to use 'None' in xml and set the loader state programitically) and you can use these states to change the loader state in xml by using data bidning


# Developed By

* Nitin Prakash
 * Gmail (nitin.prakash9911@gmail.com)
 * [LinkedIn](https://www.linkedin.com/in/nitin-prakash-b81a26156/)

## 📄 License

Floating Loader Button is released under the Apache 2.0 license.
See [LICENSE](./LICENSE) for details.

    Copyright 2020 Nitin Prakash

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
