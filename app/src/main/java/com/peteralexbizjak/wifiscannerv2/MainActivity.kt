package com.peteralexbizjak.wifiscannerv2

import android.Manifest
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.content.ContextCompat
import com.peteralexbizjak.wifiscannerv2.di.scanModule
import com.peteralexbizjak.wifiscannerv2.services.WiFiService
import com.peteralexbizjak.wifiscannerv2.ui.screens.HomeScreen
import com.peteralexbizjak.wifiscannerv2.ui.theme.WiFiScannerV2Theme
import com.peteralexbizjak.wifiscannerv2.viewmodels.ScanViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private val permissionRequestCode = 4242
    private val listOfPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CHANGE_WIFI_STATE,
        Manifest.permission.ACCESS_WIFI_STATE
    )

    private val contract = ActivityResultContracts.RequestMultiplePermissions()
    private val permissionLauncher = registerForActivityResult(contract) { results ->
        canStartScanning = results.values.all { it }
    }

    private val scanViewModel by viewModel<ScanViewModel>()
    private var canStartScanning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions(listOfPermissions, permissionRequestCode)
        setContent {
            WiFiScannerV2Theme {
                HomeScreen(scanViewModel)
            }
        }
        startKoin {
            androidContext(this@MainActivity)
            modules(scanModule)
        }

        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CHANGE_WIFI_STATE
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_WIFI_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            wifiScanningAction()
        } else {
            permissionLauncher.launch(listOfPermissions)
        }
    }

    @Suppress("DEPRECATION")
    private fun wifiScanningAction() {
        val intentFilter = IntentFilter()
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val wifiService = WiFiService(wifiManager, scanViewModel)
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        registerReceiver(wifiService, intentFilter)

        // Although this method was deprecated in API level 28, it currently still seems to work on
        // Android 11 (API level 31). I can't seem to find anything in the official documentation
        // explaining what the alternatives will be, if there will be some at all...
        val success = wifiManager.startScan()
        if (!success) {
            scanViewModel.setErroneousScanResult("Could not start scanning")
        }
    }
}
