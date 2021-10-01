package com.peteralexbizjak.wifiscannerv2.ui.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.peteralexbizjak.wifiscannerv2.R
import com.peteralexbizjak.wifiscannerv2.ui.screens.widgets.state.ContentLoading

@Preview
@ExperimentalMaterialApi
@Composable
internal fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
            )
        },
        content = {
            ContentLoading()
        }
    )
}