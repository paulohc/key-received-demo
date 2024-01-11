package com.paulohc.keyreceiveddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulohc.keyreceiveddemo.screen.MainScreen
import com.paulohc.keyreceiveddemo.screen.MainViewModel
import com.paulohc.keyreceiveddemo.ui.theme.KeyReceivedDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeyReceivedDemoTheme {
                val viewModel: MainViewModel = hiltViewModel()
                val apiKeysReady = viewModel.apiKeysReady
                val apiKeys = viewModel.apiKeys
                MainScreen(
                    apiKeysReady = apiKeysReady,
                    apiKeys = apiKeys,
                    onTryAgain = viewModel::fetchData,
                )
            }
        }
    }
}