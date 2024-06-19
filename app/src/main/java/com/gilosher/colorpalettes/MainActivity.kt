package com.gilosher.colorpalettes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gilosher.colorpalettes.navigation.Navigation
import com.gilosher.colorpalettes.ui.theme.ColorPalettesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorPalettesTheme {
                Surface(
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.background
                    )
                ) {
                    Navigation()
                }
            }
        }
    }
}