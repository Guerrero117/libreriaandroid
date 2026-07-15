package com.example.prueba.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.prueba.wear.navigation.SmartLibNavGraph
import com.example.prueba.wear.ui.theme.SmartLibWearTheme

/**
 * SmartLib - Wear OS
 *
 * Prototipo de INTERFAZ para el Smartwatch (solo diseño / UI).
 * Reutiliza el mismo modelo de datos que la app de Smartphone
 * (Usuarios, Libros, Prestamos) pero con datos de ejemplo (mock),
 * ya que el objetivo de este módulo es validar el flujo y la
 * experiencia de "micro-momentos" descrita en el documento del proyecto:
 *   1) Hub de Consultas (pantalla principal)
 *   2) Mis Préstamos -> días restantes para devolver
 *   3) Buscar Libro -> disponibilidad / stock
 *   4) Código QR -> recoger el libro físico en sucursal
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartLibWearTheme {
                SmartLibNavGraph()
            }
        }
    }
}
