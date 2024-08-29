package com.example.lili

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lili.screens.LoginScreen
import com.example.lili.ui.theme.LiliTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiliTheme {
                LoginScreen(
                    onLoginClick = {

                    },
                    onForgotPasswordClick = {
                        val intent = Intent(this, OlvideActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}