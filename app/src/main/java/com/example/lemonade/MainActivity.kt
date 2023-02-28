package com.example.lemonade

import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonTree()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonTree(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonTree(modifier: Modifier = Modifier) {

    val count = remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    val context = LocalContext.current


    if (count.value == 5)
        count.value = 1


    val imageResource = when (count.value) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val stringResource = when (count.value) {
        1 -> R.string.Tap
        2 -> R.string.Keep
        3 -> R.string.Drink
        else -> R.string.Again
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Column() {
            Text(
                text = androidx.compose.ui.res.stringResource(id = stringResource),
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)

            )

            when (count.value) {
                1 -> Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable {

                            count.value++
                            squeezeCount = (2..4).random()
                            Toast
                                .makeText(
                                    context,
                                    " Press ${squeezeCount.toString()} click on for squeeze. ",
                                    Toast.LENGTH_SHORT
                                )
                                .show()

                        }
                        .border(
                            1.7.dp, Color(0xFF69CDD8),
                            shape = RoundedCornerShape(3)

                        )

                )


                2 -> Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                count.value++
                            }
                        }
                        .border(
                            1.7.dp, Color(0xFF69CDD8),
                            shape = RoundedCornerShape(3)

                        )
                )
                3 -> Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable { count.value++ }
                        .border(
                            1.7.dp, Color(0xFF69CDD8),
                            shape = RoundedCornerShape(3)

                        )
                )
                4 -> Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable { count.value++ }
                        .border(
                            1.7.dp, Color(0xFF69CDD8),
                            shape = RoundedCornerShape(3)

                        )
                )
            }
        }
    }
}




