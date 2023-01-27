package com.uraga.moviestmdb.presentation.splash


import android.annotation.SuppressLint
import android.graphics.RuntimeShader
import android.os.Build
import android.view.animation.OvershootInterpolator
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uraga.moviestmdb.R
import com.uraga.moviestmdb.navigation.AppScreen
import kotlinx.coroutines.delay
import org.intellij.lang.annotations.Language

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.9f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )
            delay(2000L)
            navController.popBackStack()
            navController.navigate(AppScreen.MovieListScreen.route)
        }
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colors.secondary,
                                MaterialTheme.colors.primary
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY,
                        ),
                        alpha = .9f
                    )
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo splash",
                    Modifier.scale(scale.value)
                )
            }
        }
    }

}



@Language("AGSL")
val CUSTOM_SHADER = """
    uniform float2 resolution;
    layout(color) uniform half4 color;
    layout(color) uniform half4 color2;

    half4 main(in float2 fragCoord) {
        float2 uv = fragCoord/resolution.xy;

        float mixValue = distance(uv, vec2(0, 1));
        return mix(color, color2, mixValue);
    }
    """.trimIndent()

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
fun PreviewSplashScreen() {
    val blackRed = Color(0xFF1A0300)
    val redOrange = Color(0xFF861616)
    Box(modifier = Modifier.drawWithCache {
        val shader = RuntimeShader(CUSTOM_SHADER)
        val shaderBrush = ShaderBrush(shader)
        shader.setFloatUniform("resolution", size.width, size.height)
        onDrawBehind {
            shader.setColorUniform(
                "color",
                android.graphics.Color.valueOf(
                    blackRed.red, blackRed.green,
                    blackRed.blue, blackRed.alpha
                )
            )
            shader.setColorUniform(
                "color2",
                android.graphics.Color.valueOf(
                    redOrange.red, redOrange.green,
                    redOrange.blue, redOrange.alpha
                )
            )
            drawRect(shaderBrush)
        }
    }
        .fillMaxWidth()
        .height(200.dp)
    )
}
