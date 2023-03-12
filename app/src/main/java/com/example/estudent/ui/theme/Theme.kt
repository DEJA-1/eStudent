package com.example.estudent.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = mBackgroundBlack,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val HomeScreenColorPaletteDark = darkColors(
    background = mBackgroundBlack,
    onBackground = mTextWhite,
    surface = mBackgroundBlackSecondary,
    primary = mGreen
)

val HomeScreenColorPaletteLight = lightColors(
    background = mBackgroundBlack,
    onBackground = mTextWhite,
    surface = mBackgroundBlackSecondary,
    primary = mGreen
)

@Composable
fun EStudentTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()

    val colors = if (darkTheme) {
        HomeScreenColorPaletteDark
    } else {
        HomeScreenColorPaletteLight
    }


    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

    // TODO change systemBarColor for equivalent theme
    systemUiController.setSystemBarsColor(mBackgroundBlack)
}