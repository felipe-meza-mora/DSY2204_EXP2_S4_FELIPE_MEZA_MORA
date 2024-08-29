// RegistroScreen.kt
package com.example.lili.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lili.R
import androidx.compose.ui.text.input.PasswordVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(onLoginClick: () -> Unit) {
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var correo by remember { mutableStateOf(TextFieldValue("")) }
    var confirmCorreo by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var edad by remember { mutableStateOf(TextFieldValue("")) }
    var notificaciones by remember { mutableStateOf("") }

    var nombreError by remember { mutableStateOf(false) }
    var correoError by remember { mutableStateOf(false) }
    var confirmCorreoError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var edadError by remember { mutableStateOf(false) }
    var notificacionesError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Encabezado con logo y texto
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            color = Color(0xFFFFD9E4)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Lili Recetas",
                        color = Color(0xFF6F334C),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        // Formulario centrado hacia abajo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Input Nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    nombreError = it.text.isEmpty()
                },
                label = { Text("Nombre", color = Color(0xFF6F334C)) },
                isError = nombreError,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6F334C),
                    cursorColor = Color(0xFF6F334C)
                )
            )
            if (nombreError) {
                Text(text = "El nombre no puede estar vacío", color = Color(0xFFB2849A), fontSize = 12.sp)
            }

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

            // Input Confirmación de Correo
            OutlinedTextField(
                value = confirmCorreo,
                onValueChange = {
                    confirmCorreo = it
                    confirmCorreoError = it.text != correo.text
                },
                label = { Text("Confirmar Correo Electrónico", color = Color(0xFF6F334C)) },
                isError = confirmCorreoError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6F334C),
                    cursorColor = Color(0xFF6F334C)
                )
            )
            if (confirmCorreoError) {
                Text(text = "Los correos no coinciden", color = Color(0xFFB2849A), fontSize = 12.sp)
            }

            // Input Password
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = it.text.isEmpty() || it.text.length < 6
                },
                label = { Text("Contraseña", color = Color(0xFF6F334C)) },
                isError = passwordError,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6F334C),
                    cursorColor = Color(0xFF6F334C)
                )
            )
            if (passwordError) {
                Text(text = "La contraseña debe tener al menos 6 caracteres", color = Color(0xFFB2849A), fontSize = 12.sp)
            }

            // Input Confirmación de Password
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = it.text != password.text
                },
                label = { Text("Confirmar Contraseña", color = Color(0xFF6F334C)) },
                isError = confirmPasswordError,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6F334C),
                    cursorColor = Color(0xFF6F334C)
                )
            )
            if (confirmPasswordError) {
                Text(text = "Las contraseñas no coinciden", color = Color(0xFFB2849A), fontSize = 12.sp)
            }

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

            // Radio Buttons para Notificaciones
            Text("¿Desea recibir notificaciones?", color = Color(0xFF6F334C), fontSize = 18.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = notificaciones == "Si",
                        onClick = { notificaciones = "Si" },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF6F334C))
                    )
                    Text("Sí", color = Color(0xFF6F334C), modifier = Modifier.padding(start = 8.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = notificaciones == "No",
                        onClick = { notificaciones = "No" },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF6F334C))
                    )
                    Text("No", color = Color(0xFF6F334C), modifier = Modifier.padding(start = 8.dp))
                }
            }
            if (notificaciones.isEmpty()) {
                notificacionesError = true
                Text(text = "Seleccione una opción válida", color = Color(0xFFB2849A), fontSize = 18.sp)
            } else {
                notificacionesError = false
            }
        }

        // Botón de Registro y opción de login
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Validación y lógica de registro
            },
            enabled = !nombreError && !correoError && !confirmCorreoError && !passwordError && !confirmPasswordError && !edadError && !notificacionesError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6F334C),
                contentColor = Color.White
            )
        ) {
            Text(text = "Registrarse", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { onLoginClick() },
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "¿Ya tienes cuenta? Inicia sesión",
                color = Color(0xFF6F334C),
                fontSize = 18.sp
            )
        }
    }
}
