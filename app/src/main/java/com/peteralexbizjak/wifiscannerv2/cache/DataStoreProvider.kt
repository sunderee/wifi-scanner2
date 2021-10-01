package com.peteralexbizjak.wifiscannerv2.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.peteralexbizjak.wifiscannerv2.cache.models.ScanResultMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
internal object DataStoreProvider {
    private const val DATA_STORE_FILE_NAME = "wifi_scanner.pb"
    private val coroutineContext = Dispatchers.IO + SupervisorJob()

    val scanResultMessageSerializer = object : Serializer<ScanResultMessage> {
        override val defaultValue: ScanResultMessage = ScanResultMessage.getDefaultInstance()

        @Throws(
            InvalidProtocolBufferException::class,
            IllegalArgumentException::class,
            NoSuchMethodException::class,
            SecurityException::class,
            IOException::class
        )
        override suspend fun readFrom(input: InputStream): ScanResultMessage =
            withContext(coroutineContext) {
                ScanResultMessage.parseFrom(input)
            }

        @Throws(NullPointerException::class, IOException::class)
        override suspend fun writeTo(t: ScanResultMessage, output: OutputStream) =
            withContext(coroutineContext) {
                t.writeTo(output)
            }
    }

    fun <T> dataStoreProvider(context: Context, serializer: Serializer<T>): DataStore<T> =
        DataStoreFactory.create(
            serializer,
            produceFile = { File(context.filesDir, DATA_STORE_FILE_NAME) }
        )
}

