package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sharePref = remember { this.getSharedPreferences("theme", Context.MODE_PRIVATE) }
            MyApplicationTheme(darkTheme = sharePref.getBoolean("isDark", false)) {
               NavEntry()
            }
        }
    }
}
