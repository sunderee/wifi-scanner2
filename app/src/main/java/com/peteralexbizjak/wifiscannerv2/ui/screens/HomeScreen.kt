package com.peteralexbizjak.wifiscannerv2.ui.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.peteralexbizjak.wifiscannerv2.R
import com.peteralexbizjak.wifiscannerv2.ui.screens.widgets.state.ContentError
import com.peteralexbizjak.wifiscannerv2.ui.screens.widgets.state.ContentLoaded
import com.peteralexbizjak.wifiscannerv2.ui.screens.widgets.state.ContentLoading
import com.peteralexbizjak.wifiscannerv2.viewmodels.DataContainer
import com.peteralexbizjak.wifiscannerv2.viewmodels.ScanViewModel

@ExperimentalMaterialApi
@Composable
internal fun HomeScreen(
    scanViewModel: ScanViewModel
) {
    val scanViewModelState = scanViewModel.observeScanResultData().observeAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
            )
        },
        content = {
            if (scanViewModelState == null) {
                ContentLoading()
            } else {
                when (scanViewModelState) {
                    is DataContainer.Success -> {
                        ContentLoaded(
                            scanMessageData = scanViewModelState
                                .data
                                .scanResultMessageDataList
                                ?.toList() ?: emptyList()
                        )
                    }
                    is DataContainer.Error -> {
                        ContentError(
                            errorMessage = scanViewModelState.exception
                        )
                    }
                    is DataContainer.Loading -> {
                        ContentLoading()
                    }
                }
            }
        }
    )
}