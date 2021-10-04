package com.peteralexbizjak.wifiscannerv2.viewmodels

import android.net.wifi.ScanResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class ScanViewModel : ViewModel() {
    private val coroutineContext = Dispatchers.IO
    private val scanResultData by lazy { MutableLiveData<DataContainer<List<ScanResult>>>() }

    fun updateScanResults(scanResults: List<ScanResult>) {
        viewModelScope.launch(coroutineContext) {
            scanResultData.postValue(DataContainer.Loading)

            if (scanResults.isNotEmpty()) {
                scanResultData.postValue(DataContainer.Success(scanResults))
            } else {
                scanResultData.postValue(DataContainer.Error("No Wi-Fi networks found"))
            }
        }
    }

    fun setErroneousScanResult(exceptionMessage: String) {
        viewModelScope.launch(coroutineContext) {
            scanResultData.postValue(DataContainer.Error(exceptionMessage))
        }
    }

    fun observeScanResultData(): LiveData<DataContainer<List<ScanResult>>> = scanResultData
}