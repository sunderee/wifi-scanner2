package com.peteralexbizjak.wifiscannerv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.peteralexbizjak.wifiscannerv2.ui.screens.HomeScreen
import com.peteralexbizjak.wifiscannerv2.ui.theme.WiFiScannerV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WiFiScannerV2Theme {
                HomeScreen()
            }
        }
    }
}
