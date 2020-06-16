<a href="https://user-images.githubusercontent.com/32475878/80305954-d5a36980-87dd-11ea-84c4-905a7abc881c.png"><img src="https://user-images.githubusercontent.com/32475878/80305954-d5a36980-87dd-11ea-84c4-905a7abc881c.png"></a>
</br>
[![Generic badge](https://img.shields.io/badge/release-v1.1.0-blue.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/platform-android-brightgreen.svg?logo=android)](https://shields.io/)
[![made-with-kotlin](https://img.shields.io/badge/Made%20with-kotlin-1f425f.svg?logo=kotlin)](https://github.com/JetBrains/kotlin)
[![Generic badge](https://img.shields.io/badge/MinimumSdk-16-Green.svg)](https://shields.io/)
<a href=""><img alt="Github Downloads (total)" src="https://img.shields.io/github/downloads/NitinPraksash9911/floating-loader-button/total.svg"/></a>
 [![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/NitinPraksash9911/floating-loader-button/issues)
</br>

<a href="https://github.com/NitinPraksash9911/floating-loader-button">
<img align="right"  src="https://user-images.githubusercontent.com/32475878/80308271-a3990400-87eb-11ea-9d98-a1c8b86d438d.gif" width="200" height="410" /></a>

<p><h1 align="left">Floating Loader Button</h1></p>

<img src="https://user-images.githubusercontent.com/32475878/80315162-782a0f80-8813-11ea-9e67-8956264d58d4.png"/> Android library to show circular loader within floating button
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
## Create view in xml

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
app:loaderBackgroundColor | to change the `FloatingLoaderButton` background color & by default background color is black
app:loaderFabSize | to change the size of `FloatingLoaderButton` & it's available in three sizes `Medium`, `Small`, & `Large` choose one from these
app:loadingIconColor | to change the arrow-icon color and by default arrow-icon color is white
app:loadingStatus | use to define the state of loader & it has three states  1. `None` for initial stage when doing nothing, 2. `Loading` to start circular loading, 3. `Finish` to stop circular loading (recommended to use `None` in xml and set the loader state programmatically) and you can use these states to change the loader state in xml when using data binding

### Size

`Large` | `Medium` | `Small` 
--------- | -------- | -------
 | <center> <img src="https://user-images.githubusercontent.com/32475878/80315868-0607f980-8818-11ea-8f87-e9e5463887a5.gif" width="150" height="120" /></center> | <center> <img  src="https://user-images.githubusercontent.com/32475878/80316393-29807380-881b-11ea-8ae0-a14aab99e0ee.gif" width="130" height="100" /></center> | <center> <img  src="https://user-images.githubusercontent.com/32475878/80316026-0359d400-8819-11ea-8d5e-38ddccffcbf9.gif" width="90" height="80" /></center> |

## Kotlin

This below example shows only how you can use `FloatingLoaderButton` when calling an api and you can also use this anywhere where something going to take some time in andorid application such as `Background Task`, `Database Operation`, `Network Operation` etc...

```kotlin
 val floatingLoaderButton: FloatingLoaderButton = findViewById<FloatingLoaderButton>(R.id.floatingLoaderBtn)
    
        floatingLoaderButton.setOnClickListener {

            // to start circular animation when api call starts
            floatingLoaderButton.setLoadingStatus(FloatingLoaderButton.LoaderStatus.LOADING)

            val apiInterface = ApiInterface.create().getData()
            
            apiInterface.enqueue(object : Callback<List<Data>> {
            
                override fun onResponse(call: Call<List<Data>>?, response: Response<List<Data>>?) {

                    if (response?.body() != null)
                        recyclerAdapter.setData(response.body()!!)
                       
                    // to stop circular animation when api call success
                    floatingLoaderButton.setLoadingStatus(FloatingLoaderButton.LoaderStatus.FINISH)
                }

                override fun onFailure(call: Call<List<Data>>?, t: Throwable?) {

                    //to stop circular animation when api call fails
                    floatingLoaderButton.setLoadingStatus(FloatingLoaderButton.LoaderStatus.FINISH)

                }
            })
        }
 ```       

## Proguard

In order to use this library with proguard you need to add this line to your `proguard.cfg`:

```grovy
-keep class `in`.nitin.library.FloatingLoaderButton.** { *; }
````

## Developed By

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
