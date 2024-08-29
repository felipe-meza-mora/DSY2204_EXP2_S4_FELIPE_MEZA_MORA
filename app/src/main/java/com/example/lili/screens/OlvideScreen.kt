package com.example.lili.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OlvideScreen(onBackToLogin: () -> Unit) {
    var correo by remember { mutableStateOf(TextFieldValue("")) }
    var edad by remember { mutableStateOf(TextFieldValue("")) }
    var showPasswordModal by remember { mutableStateOf(false) }
    var recoveredPassword by remember { mutableStateOf("tucontraseña123") }

    var correoError by remember { mutableStateOf(false) }
    var edadError by remember { mutableStateOf(false) }

    // Validación de campos
    fun validateFields(): Boolean {
        correoError = correo.text.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(correo.text).matches()
        edadError = edad.text.isEmpty() || edad.text.toIntOrNull()?.let { it < 18 } ?: true
        return !correoError && !edadError
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input Correo
        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                correoError = it.text.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(it.text).matches()
            },
            label = { Text("Correo Electrónico", color = Color(0xFF6F334C)) },
            isError = correoError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6F334C),
                cursorColor = Color(0xFF6F334C)
            )
        )
        if (correoError) {
            Text(text = "Correo inválido", color = Color(0xFFB2849A), fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input Edad
        OutlinedTextField(
            value = edad,
            onValueChange = {
                edad = it
                edadError = it.text.isEmpty() || it.text.toIntOrNull()?.let { it < 18 } ?: true
            },
            label = { Text("Edad", color = Color(0xFF6F334C)) },
            isError = edadError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6F334C),
                cursorColor = Color(0xFF6F334C)
            )
        )
        if (edadError) {
            Text(text = "Debes ser mayor de 18 años", color = Color(0xFFB2849A), fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Recuperar Contraseña
        Button(
            onClick = {
                if (validateFields()) {
                    showPasswordModal = true
                }
            },
            enabled = !correoError && !edadError,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6F334C),
                contentColor = Color.White
            )
        ) {
            Text(text = "Recuperar Contraseña", fontSize = 20.sp)
        }
    }

    // Modal de Contraseña Recuperada
    if (showPasswordModal) {
        AlertDialog(
            onDismissRequest = {
                showPasswordModal = false
                onBackToLogin()
            },
            title = { Text(text = "Contraseña Recuperada") },
            text = {
                Column {
                    Text(text = "Tu contraseña es:")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = recoveredPassword,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF6F334C)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showPasswordModal = false
                        onBackToLogin()
                    }
                ) {
                    Text(text = "Cerrar")
                }
            }
        )
    }
}
