/*
    UI Automator Test

        - Open App from Home Screen through Launcher

        - In Main Activity, click a button, "Open Activity Explicitly" to start Second Activity

        - Check if a specific text exists?

 */

package com.example.firstapplication.uiautomator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class UiAutomatorTest {
    private val LAUNCH_TIMEOUT = 5000L

    private val APP_PACKAGE = "com.example.firstapplication"

    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialization of UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Starting from Home Screen
        device.pressHome()

        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, CoreMatchers.notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            APP_PACKAGE
        )?.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app start
        device.wait(
            Until.hasObject(By.pkg(APP_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    @Test
    fun testStartSecondActivity() {
        val secondActivityButton =
            device.wait(Until.findObject(By.text("Start Activity Explicitly")), LAUNCH_TIMEOUT)
        Assert.assertNotNull("Start Activity Explicitly button not found", secondActivityButton)

        secondActivityButton.click()
    }

    @Test
    fun testForListedChallenge() {
        val secondActivityButton =
            device.wait(Until.findObject(By.text("Start Activity Explicitly")), LAUNCH_TIMEOUT)

        if (secondActivityButton != null) {
            secondActivityButton.click()

            val challengeText =
                device.wait(Until.findObject(By.text("Device Fragmentation")), LAUNCH_TIMEOUT)
            Assert.assertNotNull(
                "The challenge, \"Device Fragmentation\", not found.",
                challengeText
            )
        } else {
            Assert.fail("Start Activity Explicitly button not found.")
        }
    }
}