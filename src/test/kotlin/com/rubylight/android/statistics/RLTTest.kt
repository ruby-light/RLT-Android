package com.rubylight.android.statistics

import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class RLTTest: BaseTest() {

    @Before
    override fun setUp() {
        super.setUp()
    }

    @After
    override fun setDown() {
        super.setDown()
    }

    @Test
    fun testNotInitialized() {
        RLT.getClient()
            .setUserId("FIRST")
            .logUserProperties(UserProperties())
            .logEvent("Event")
            .flush()
    }

    @Test
    fun testInitialized() {
        RLT.initialize(context, "<API_KEY>",
            InitConfig()
                .setServerUrl("https://stats.mydomain.com")
                .enableStartAppEvent()
//                .enableSessionTracking(app)
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

        RLT.getClient().logEvent("StartChart",
            EventProperties().set("type", "private"))

        RLT.getClient().logEvent("EndChart")
    }

    @Test
    fun testSetUserId() {
        val userId = "USER_1"
        val userId2 = "USER_2"

        val instance = RLT.getClient()

        RLT.initialize(context, "API_KEY")
        val statisticsImpl = getStatisticsImpl()
        instance.flush()
        Shadows.shadowOf(statisticsImpl.logThread.looper).runOneTask()

//        Truth.assertThat(context.getDatabasePath(DatabaseSQLiteImpl.DATABASE_NAME).exists()).isTrue()
        Truth.assertThat(statisticsImpl.deviceId).isNotNull()
        Truth.assertThat(statisticsImpl.deviceId)
            .isEqualTo(statisticsImpl.getDeviceId())
        Truth.assertThat(statisticsImpl.deviceId)
            .isEqualTo(statisticsImpl.identifyStorage.getDeviceId())
        Truth.assertThat(statisticsImpl.userId).isNull()
        Truth.assertThat(statisticsImpl.identifyStorage.getUserId()).isNull()
        Truth.assertThat(statisticsImpl.sequence).isEqualTo(0)
        Truth.assertThat(statisticsImpl.reportsStorage.getMaxSequence()).isEqualTo(0)

        instance.setUserId(userId)

        Shadows.shadowOf(statisticsImpl.logThread.looper).runOneTask()
        Truth.assertThat(statisticsImpl.userId).isEqualTo(userId)
        Truth.assertThat(statisticsImpl.identifyStorage.getUserId()).isEqualTo(userId)

        instance.setUserId(userId2)

        Shadows.shadowOf(statisticsImpl.logThread.looper).runOneTask()
        Truth.assertThat(statisticsImpl.userId).isEqualTo(userId2)
        Truth.assertThat(statisticsImpl.identifyStorage.getUserId()).isEqualTo(userId2)
    }

    @Test
    fun resetUserId() {
        val userId = "USER_1"
        val userId2 = "USER_2"

        val instance = RLT.getClient()

        RLT.initialize(context, "API_KEY")
        val statisticsImpl = getStatisticsImpl()

        Truth.assertThat(statisticsImpl.deviceId).isNotNull()
        Truth.assertThat(statisticsImpl.deviceId)
            .isEqualTo(statisticsImpl.identifyStorage.getDeviceId())

        val deviceId = statisticsImpl.deviceId

        Truth.assertThat(statisticsImpl.userId).isNull()
        Truth.assertThat(statisticsImpl.identifyStorage.getUserId()).isNull()
        Truth.assertThat(statisticsImpl.sequence).isEqualTo(0)
        Truth.assertThat(statisticsImpl.reportsStorage.getMaxSequence()).isEqualTo(0)

        run {
            instance.setUserId(userId)
            Shadows.shadowOf(statisticsImpl.logThread.looper).runOneTask()

            Truth.assertThat(statisticsImpl.userId).isEqualTo(userId)
            Truth.assertThat(statisticsImpl.identifyStorage.getUserId()).isEqualTo(userId)

            Truth.assertThat(statisticsImpl.deviceId).isEqualTo(deviceId)
            Truth.assertThat(statisticsImpl.identifyStorage.getDeviceId()).isEqualTo(deviceId)
        }

        run {
            instance.resetUserId(false)
            Shadows.shadowOf(statisticsImpl.logThread.looper).runOneTask()

            Truth.assertThat(statisticsImpl.userId).isNull()
            Truth.assertThat(statisticsImpl.identifyStorage.getUserId()).isNull()

            Truth.assertThat(statisticsImpl.deviceId).isEqualTo(deviceId)
            Truth.assertThat(statisticsImpl.identifyStorage.getDeviceId()).isEqualTo(deviceId)
        }

        run {
            instance.setUserId(userId2)
            Shadows.shadowOf(statisticsImpl.logThread.looper).runOneTask()

            Truth.assertThat(statisticsImpl.userId).isEqualTo(userId2)
            Truth.assertThat(statisticsImpl.identifyStorage.getUserId()).isEqualTo(userId2)

            Truth.assertThat(statisticsImpl.deviceId).isEqualTo(deviceId)
            Truth.assertThat(statisticsImpl.identifyStorage.getDeviceId()).isEqualTo(deviceId)
        }

        run {
            instance.resetUserId(true)
            Shadows.shadowOf(statisticsImpl.logThread.looper).runOneTask()

            Truth.assertThat(statisticsImpl.userId).isNull()
            Truth.assertThat(statisticsImpl.identifyStorage.getUserId()).isNull()

            Truth.assertThat(statisticsImpl.deviceId).isNotEqualTo(deviceId)
            Truth.assertThat(statisticsImpl.identifyStorage.getDeviceId()).isEqualTo(statisticsImpl.deviceId)
        }
    }


    @Test
    fun testSetUserProperties() {

        val initConfig = InitConfig()
//            .setServerUrl("http://localhost:8889/collectNewSdk")
//            .setServerUrl("http://18.222.182.218:8989/collectNewSdk")
            .setDevicePropertyConfig(DevicePropertyConfig().trackCacheProperty("country", { "lv" }))
            .enableStartAppEvent()

        RLT.initialize(context, "lmk", initConfig)
        val statisticsImpl = getStatisticsImpl()

        val logLooper = Shadows.shadowOf(statisticsImpl.logThread.looper)
        val sendLooper = Shadows.shadowOf(statisticsImpl.uploader.sendThread.looper)

        RLT.getClient()
            .logUserProperties(UserProperties().set("gender", "man"))
            .logEvent("MyEvent")
            .logEvent("MyEvent2", EventProperties()
                .set("type", "set")
                .set("bool", true)
                .set("bt", 23.toByte())
                .set("sh", 232.toShort())
                .set("duration", 231)
                .set("duration ", 233L)
            )

//        val up = UserProperties()
//        for (i in 1 .. Formats.PROPERTIES_MAX_COUNT + 1) {
//            up.set("property$i", "aa")
//        }
//        Statistics.getClient().logUserProperties(up)


        logLooper.runToEndOfTasks()
        logLooper.runToEndOfTasks()
        sendLooper.runToEndOfTasks()
        logLooper.runToEndOfTasks()
    }

    private fun getStatisticsImpl(): ClientImpl {
        val wrapper = RLT.getClient() as ClientWrapper
        return wrapper.client as ClientImpl
    }

}