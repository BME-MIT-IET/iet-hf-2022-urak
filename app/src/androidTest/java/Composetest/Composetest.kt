package Composetest


import android.net.Uri
import android.os.SystemClock
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.Navigation.findNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.awaitAll
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sk.kasper.space.R
import sk.kasper.space.MainActivity
import sk.kasper.ui_common.utils.createSlideAnimNavOptions


import sk.kasper.ui_timeline.LaunchListItem
import sk.kasper.ui_timeline.TimelineFragment

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class Composetest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun settingstest(){
        composeTestRule.activityRule.scenario.onActivity {
            findNavController(it, R.id.nav_host_fragment)
                .navigate(R.id.settingsFragment)
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("settingslog")
        composeTestRule.onNodeWithText("Choose theme").performClick()
        composeTestRule.onNodeWithText("Dark").performClick()
        composeTestRule.onNodeWithText("Choose theme").performClick()
        composeTestRule.onNodeWithText("Light").performClick()

        composeTestRule.onNodeWithText("Unconfirmed launches").performClick()
        composeTestRule.onNodeWithText("Unconfirmed launches").performClick()

        composeTestRule.onNodeWithText("Show before").performClick()
        composeTestRule.onNodeWithText("30 minutes").performClick()
        composeTestRule.onNodeWithText("30 minutes").assertExists()
        composeTestRule.onNodeWithText("Show before").performClick()
        composeTestRule.onNodeWithText("1 hour").performClick()

        composeTestRule.onNodeWithText("Api endpoint").performClick()
        composeTestRule.onNodeWithText("Raspberry").performClick()
        composeTestRule.onNodeWithText("Raspberry").assertExists()
        composeTestRule.onNodeWithText("Api endpoint").performClick()
        composeTestRule.onNodeWithText("Production").performClick()

        composeTestRule.onNodeWithTag("SettingsDropDownMenuTag").performClick()
        composeTestRule.onNodeWithTag("SettingsDropDownMenuItem").performClick()
        composeTestRule.onNodeWithText("Rocktly is built with help of these libraries").assertExists()
        composeTestRule.onNodeWithTag("LibraryBackButton").performClick()
        composeTestRule.onNodeWithTag("SettingsBackButton").performClick()
    }

    @Test
    fun launchlistItemTest(){
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("timelinelog")
        composeTestRule.onNodeWithText("May").assertExists()
        composeTestRule.onNodeWithText("Falcon 9 Full Thrust • Merah Putih (Telkom-4)").assertExists()
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("launchspeclog")
    }

    @Test
    fun timelineItemDetailTest(){
        composeTestRule.onNodeWithText("Falcon 9 Full Thrust • Merah Putih (Telkom-4)").assertExists()
        composeTestRule.onNodeWithText("Falcon 9 Full Thrust • Merah Putih (Telkom-4)").performClick()
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("launchspeclog")
        composeTestRule.onNodeWithText("Rocket info").assertExists()
        composeTestRule.onNodeWithText(": Falcon 9 Full Thrust").assertExists()
        composeTestRule.onNodeWithText("Merah Putih (Telkom-4)").assertExists()
        composeTestRule.onNodeWithText(": 3.7 m").assertExists()
        Thread.sleep(2000)
    }

    @Test
    fun composePlayGroundtabsandfunctionstest() {
        composeTestRule.activityRule.scenario.onActivity {
            findNavController(it, R.id.nav_host_fragment)
                .navigate(R.id.composePlaygroundFragment)
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("playgroundroot")
        composeTestRule.onNodeWithText("ANIM").performClick()
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("playgroundanim")
        composeTestRule.onNodeWithText("Toggle").assertExists()

        composeTestRule.onNodeWithText("COMPONENTS").performClick()
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("playgroundcomponents")
        composeTestRule.onNodeWithText("TEXT").assertExists()
        composeTestRule.onNodeWithText("OUTLINED").assertExists()
        composeTestRule.onNodeWithTag("playgroundScrollableRow")
        .performTouchInput {
            swipeLeft()
        }

        composeTestRule.onNodeWithText("TYPE").performClick()
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("playgroundtype")
        composeTestRule.onNodeWithText("COLOR").performClick()
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("playgroundcolor")
        composeTestRule.onNodeWithText("SHAPE").performClick()
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("playgroundshape")
        composeTestRule.onNodeWithText("Shape small").assertExists()
        composeTestRule.onNodeWithText("Shape medium").assertExists()
        composeTestRule.onNodeWithText("Shape large").assertExists()
    }
}