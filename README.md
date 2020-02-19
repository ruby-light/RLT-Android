# RLT-Android

Native Android SDK

## Setup

Download via Gradle:

```gradle
implementation 'com.rubylight:rlt-android:1.0.1'
```

Download via Maven:

```xml
<dependency>
  <groupId>com.rubylight</groupId>
  <artifactId>rlt-android</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

## Usage

#### Kotlin

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
    
    RLT.getClient().logUserProperties(
        UserProperties().set("gender", "male"))

    RLT.getClient().setUserId("123123-341231")

    RLT.getClient().logEvent("StartConversation",
        EventProperties().set("type", "private"))

    RLT.getClient().logEvent("EndConversation")

```

#### Java

```java

        RLT.INSTANCE.initialize(context, "<API_KEY>",
                new InitConfig()
                        .setServerUrl("https://stats.mydomain.com")
                        .enableStartAppEvent()
                        .enableSessionTracking(app)
                        .setDevicePropertyConfig(
                                new DevicePropertyConfig()
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
        );

        RLT.INSTANCE.getClient().logUserProperties(new UserProperties().set("gender", "male"));

        RLTClient client = RLT.INSTANCE.getClient();
        client.setUserId("123123-341231");

        client.logEvent("StartConversation", new EventProperties().set("type", "private"));

        client.logEvent("EndConversation");
        
        client.flush();

```

## License 

`RLT-Android` is distributed under the terms and conditions of the [MIT license](https://github.com/ruby-light/RLT-Android/blob/master/LICENSE).