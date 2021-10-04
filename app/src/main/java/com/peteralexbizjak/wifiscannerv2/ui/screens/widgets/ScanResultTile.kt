package com.peteralexbizjak.wifiscannerv2.ui.screens.widgets

import android.net.wifi.ScanResult
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.peteralexbizjak.wifiscannerv2.ui.theme.colorProductSecondary

@ExperimentalMaterialApi
@Composable
internal fun ScanResultTile(model: ScanResult) {
    ListItem(
        text = {
            Text(
                text = "${model.SSID} (${model.BSSID})",
                color = colorProductSecondary,
                fontWeight = FontWeight.Bold
            )
        },
        secondaryText = {
            Column {
                Text(
                    model.capabilities.split("][")
                        .joinToString(", ") {
                            it.replace("[", "").replace("]", "")
                        }
                )
                Text("Channel number: #${convertFrequencyToChannel(model.frequency)} (${model.frequency} MHz)")
            }
        }
    )
}

private fun convertFrequencyToChannel(frequency: Int): Int = when (frequency) {
    in 2412..2484 -> (frequency - 2412) / 5 + 1
    in 5170..5825 -> (frequency - 5170) / 5 + 34
    else -> -1
}