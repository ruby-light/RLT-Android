# Example #

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