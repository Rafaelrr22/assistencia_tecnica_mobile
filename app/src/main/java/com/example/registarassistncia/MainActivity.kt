package com.example.registarassistncia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.registarassistncia.navigation.AppNavigation
import com.example.registarassistncia.ui.theme.RegistarAssistênciaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RegistarAssistênciaTheme {
                AppNavigation()
            }
        }
    }
}

