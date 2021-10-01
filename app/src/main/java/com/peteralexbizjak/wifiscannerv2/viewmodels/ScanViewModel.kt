package com.peteralexbizjak.wifiscannerv2.viewmodels

import android.net.wifi.ScanResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peteralexbizjak.wifiscannerv2.cache.models.ScanResultMessage
import com.peteralexbizjak.wifiscannerv2.cache.repositories.IScanResultMessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class ScanViewModel(
    private val scanResultMessageRepository: IScanResultMessageRepository
) : ViewModel() {
    private val coroutineContext = Dispatchers.IO
    private val scanResultData by lazy { MutableLiveData<DataContainer<ScanResultMessage>>() }

    fun updateScanResults(scanResults: List<ScanResult>) {
        viewModelScope.launch(coroutineContext) {
            scanResultData.postValue(DataContainer.Loading)

            if (scanResults.isNotEmpty()) {
                scanResultMessageRepository.storeScanResult(scanResults)
                val newScanResult = scanResultMessageRepository.retrieveScanResultMessages()
                if (newScanResult != null) {
                    scanResultData.postValue(DataContainer.Success(newScanResult))
                } else {
                    scanResultData.postValue(DataContainer.Error("Caching error"))
                }
            }

        }
    }

    fun setErroneousScanResult(exceptionMessage: String) {
        viewModelScope.launch(coroutineContext) {
            scanResultData.postValue(DataContainer.Error(exceptionMessage))
        }
    }

    fun observeScanResultData(): LiveData<DataContainer<ScanResultMessage>> = scanResultData
}