package com.peteralexbizjak.wifiscannerv2.di

import com.peteralexbizjak.wifiscannerv2.cache.DataStoreProvider
import com.peteralexbizjak.wifiscannerv2.cache.repositories.IScanResultMessageRepository
import com.peteralexbizjak.wifiscannerv2.cache.repositories.implementations.ScanResultMessageRepository
import com.peteralexbizjak.wifiscannerv2.viewmodels.ScanViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val scanModule = module {
    single {
        DataStoreProvider.dataStoreProvider(
            androidContext(),
            DataStoreProvider.scanResultMessageSerializer
        )
    }
    single<IScanResultMessageRepository> { ScanResultMessageRepository(dataStoreInstance = get()) }

    viewModel { ScanViewModel(scanResultMessageRepository = get()) }
}