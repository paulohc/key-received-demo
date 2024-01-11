package com.paulohc.keyreceiveddemo.screen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulohc.keyreceiveddemo.domain.EncryptedPreferences
import com.paulohc.keyreceiveddemo.domain.KeyProviderService
import com.paulohc.keyreceiveddemo.model.Keys
import com.paulohc.keyreceiveddemo.util.KeyPairHandler
import com.paulohc.keyreceiveddemo.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: EncryptedPreferences,
    private val keyProviderService: KeyProviderService,
) : ViewModel() {

    var apiKeysReady by mutableStateOf<RequestState<Boolean>>(RequestState.Idle)
        private set

    var apiKeys by mutableStateOf<Keys?>(null)
        private set

    init {
        fetchData()
    }

    private suspend fun fetchApiKeysAndStoreThemSecurely(): RequestState<Boolean> {
        return try {
            KeyPairHandler.generateKeyPair()
            val publicKey = KeyPairHandler.getPublicKey()
            val fetchedData = fetchEncryptedApiKeys(publicKey = publicKey)
            if (fetchedData != null) {
                val decryptedData = KeyPairHandler.decryptTheData(encryptedData = fetchedData)
                val keys = Json.decodeFromString<Keys>(decryptedData)
                val result = preferences.saveEncryptedData(keys = keys)
                apiKeys = preferences.readEncryptedData()
                RequestState.Success(data = result)
            } else {
                throw  ApiKeysException(message = "Failed to Fetch API Keys.")
            }
        } catch (e: Exception) {
            RequestState.Error(message = "${e.message}")
        }
    }

    private suspend fun fetchEncryptedApiKeys(publicKey: String): String? {
        val response = keyProviderService.getEncryptedApiKeys(publicKey = publicKey)
        return if (response.isSuccessful) response.body()
        else throw ApiKeysException(message = response.message())
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.Main) {
            apiKeysReady = RequestState.Loading
            delay(1000)
            apiKeysReady = fetchApiKeysAndStoreThemSecurely()
        }
    }
}

class ApiKeysException(message: String) : Exception(message)