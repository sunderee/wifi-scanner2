package com.peteralexbizjak.wifiscannerv2.viewmodels

internal sealed class DataContainer<out T> {
    object Loading : DataContainer<Nothing>()
    data class Success<out T>(val data: T) : DataContainer<T>()
    data class Error(val exception: String) : DataContainer<Nothing>()
}