# RLT-Android

Native Android SDK

### Download

Download via Gradle:

```gradle
implementation 'com.rubylight:rlt-android:1.0.1'
```

### Usage

Initialize:
```kotlin

    RLT.initialize(context, "<API_KEY>", 
        InitConfig()
            .setServerUrl("https://stats.mydomain.com")
            .enableStartAppEvent()
            .enableSessionTracking(app)
            .setDevicePropertyConfig(
                DevicePropertyConfig()
                    .trackPlatform()
                    .trackManufacturer()
                    .trackBrand()
                    .trackModel()
                    .trackOsVersion()
                    .trackCountry()
                    .trackAppVersion()
                    .trackCarrier()
                    .trackLanguage()
            )
    )
        
```

Log user properties:
```kotlin
    
    RLT.getClient().logUserProperties(
        UserProperties().set("gender", "male"))

```

Set application user id: 
```kotlin

    RLT.getClient().setUserId("123123-341231")

```

Log events: 
```kotlin

    RLT.getClient().logEvent("StartConversation",
        EventProperties().set("type", "private"))

    RLT.getClient().logEvent("EndConversation")

```

### License 

```
Copyright 2020 Rubylight

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.```

