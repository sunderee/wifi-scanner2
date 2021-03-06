package com.peteralexbizjak.wifiscannerv2.ui.screens.widgets.state

import android.net.wifi.ScanResult
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.peteralexbizjak.wifiscannerv2.ui.screens.widgets.ScanResultTile

@ExperimentalMaterialApi
@Composable
internal fun ContentLoaded(scanMessageData: List<ScanResult>) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight()
    ) {
        items(scanMessageData) { model ->
            ScanResultTile(model = model)
        }
    }
}