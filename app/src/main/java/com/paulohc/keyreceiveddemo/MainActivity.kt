package com.paulohc.keyreceiveddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.paulohc.keyreceiveddemo.ui.theme.KeyReceivedDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeyReceivedDemoTheme {

            }
        }
    }
}