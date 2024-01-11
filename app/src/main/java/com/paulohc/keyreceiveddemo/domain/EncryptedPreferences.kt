package com.paulohc.keyreceiveddemo.domain

import com.paulohc.keyreceiveddemo.model.Keys

interface EncryptedPreferences {
    fun saveEncryptedData(keys: Keys): Boolean
    fun readEncryptedData(): Keys?
    fun areApiKeysReady(): Boolean
}