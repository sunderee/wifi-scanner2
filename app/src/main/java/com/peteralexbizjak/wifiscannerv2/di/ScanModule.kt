package com.peteralexbizjak.wifiscannerv2.di

import com.peteralexbizjak.wifiscannerv2.viewmodels.ScanViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val scanModule = module {
    viewModel { ScanViewModel() }
}