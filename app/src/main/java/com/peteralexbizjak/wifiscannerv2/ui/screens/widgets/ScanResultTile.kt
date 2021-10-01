package com.peteralexbizjak.wifiscannerv2.ui.screens.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.peteralexbizjak.wifiscannerv2.cache.models.ScanResultMessage
import com.peteralexbizjak.wifiscannerv2.ui.theme.colorProductSecondary

@ExperimentalMaterialApi
@Composable
internal fun ScanResultTile(
    model: ScanResultMessage.ScanResultMessageData
) {
    ListItem(
        text = {
            Text(
                text = "${model.ssid} (${model.macAddress})",
                color = colorProductSecondary,
                fontWeight = FontWeight.Bold
            )
        },
        secondaryText = {
            Column {
                Text(model.capabilitiesList.joinToString())
                Text("Channel number: #${model.channelNumber} (${model.frequency} MHz)")
            }
        }
    )
}