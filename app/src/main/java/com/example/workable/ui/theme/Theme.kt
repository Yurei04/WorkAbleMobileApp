package com.workable.mobile.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val LightColors = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    secondary = Secondary,
    background = Background,
    surface = SurfaceColor,
    onBackground = Text,
    onSurface = Text,
)

@Composable
fun WorkAbleTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(
            titleLarge = androidx.compose.ui.text.TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            titleMedium = androidx.compose.ui.text.TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
            bodyMedium = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
            bodySmall = androidx.compose.ui.text.TextStyle(fontSize = 12.sp)
        ),
        shapes = Shapes(),
        content = content
    )
}
