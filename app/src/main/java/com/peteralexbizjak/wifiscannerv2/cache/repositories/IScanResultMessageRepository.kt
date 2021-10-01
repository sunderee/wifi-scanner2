package com.peteralexbizjak.wifiscannerv2.cache.repositories

import android.net.wifi.ScanResult
import com.peteralexbizjak.wifiscannerv2.cache.models.ScanResultMessage

internal interface IScanResultMessageRepository {
    suspend fun storeScanResult(scanResults: List<ScanResult>)
    suspend fun retrieveScanResultMessages(): ScanResultMessage?
    suspend fun flush()
}