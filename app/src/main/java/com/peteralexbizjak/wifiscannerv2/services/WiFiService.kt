package com.peteralexbizjak.wifiscannerv2.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import com.peteralexbizjak.wifiscannerv2.viewmodels.ScanViewModel

internal class WiFiService(
    private val scanViewModel: ScanViewModel
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val wifiManager = context?.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        if (intent?.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false) == true) {
            scanViewModel.updateScanResults(wifiManager?.scanResults ?: emptyList())
        }
    }
}