package com.peteralexbizjak.wifiscannerv2.cache.repositories.implementations

import android.net.wifi.ScanResult
import androidx.datastore.core.DataStore
import com.peteralexbizjak.wifiscannerv2.cache.models.ScanResultMessage
import com.peteralexbizjak.wifiscannerv2.cache.repositories.IScanResultMessageRepository
import kotlinx.coroutines.flow.firstOrNull

internal class ScanResultMessageRepository(
    private val dataStoreInstance: DataStore<ScanResultMessage>
) : IScanResultMessageRepository {
    override suspend fun storeScanResult(scanResults: List<ScanResult>) {
        dataStoreInstance.updateData {
            it.toBuilder()
                .clear()
                .addAllScanResultMessageData(
                    scanResults.map { scanResult ->
                        ScanResultMessage.ScanResultMessageData.newBuilder().apply {
                            ssid = scanResult.SSID
                            macAddress = scanResult.BSSID
                            addAllCapabilities(
                                scanResult.capabilities
                                    .split("][")
                                    .map { capability ->
                                        capability
                                            .replace("[", "")
                                            .replace("]", "")
                                    }
                            )
                            frequency = scanResult.frequency
                            channelNumber = convertFrequencyToChannel(scanResult.frequency)
                            signalLevel = scanResult.level
                        }.build()
                    }
                )
                .build()
        }
    }

    override suspend fun retrieveScanResultMessages(): ScanResultMessage? {
        val data = dataStoreInstance.data.firstOrNull()
        return data
    }

    private fun convertFrequencyToChannel(frequency: Int): Int = when (frequency) {
        in 2412..2484 -> (frequency - 2412) / 5 + 1
        in 5170..5825 -> (frequency - 5170) / 5 + 34
        else -> -1
    }
}