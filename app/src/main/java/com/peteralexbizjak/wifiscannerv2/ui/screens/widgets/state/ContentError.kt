package com.peteralexbizjak.wifiscannerv2.ui.screens.widgets.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.peteralexbizjak.wifiscannerv2.R
import com.peteralexbizjak.wifiscannerv2.ui.theme.colorProductPrimary

@Composable
internal fun ContentError(
    errorMessage: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painterResource(R.drawable.ic_warning),
            "Warning",
            modifier = Modifier
                .width(56.dp)
                .height(56.dp),
            colorFilter = ColorFilter.tint(
                colorProductPrimary
            )
        )
        Text(
            text = "Error!",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.body2,
        )
    }
}