package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp(modifier = Modifier)
            }
        }
    }
}

@Preview
@Composable
fun LemonadeAppPreview(modifier: Modifier = Modifier) {
    LemonadeApp(modifier)
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    Column {
        modifier
            .fillMaxSize()
        LemonadeHeader(
            modifier
                .fillMaxHeight(0.08f)
        )
        Column (
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ){
            LemonadeAppContent(modifier)
        }

    }
}

@Preview
@Composable
fun LemonadeHeader(modifier: Modifier = Modifier) {
    Column(modifier= modifier
        .fillMaxWidth()
        .background(
            colorResource(
                R.color.lemonade
            )
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Lemonade",
            fontSize = 26.sp
        )
    }
}

@Preview
@Composable
fun LemonadeAppContent(modifier: Modifier = Modifier) {
    var processStep by remember {
        mutableIntStateOf(1)
    }

    var processImage by remember {
        mutableIntStateOf(R.drawable.lemon_tree)
    }

    var processText by remember {
        mutableIntStateOf(R.string.lemonStepOneText)
    }

    var stepTwoRandomNumber by remember {
        mutableIntStateOf((2..4).random())
    }

    var stepTwoTapCounter by remember {
        mutableIntStateOf(0)
    }
    Column (
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Button(

            onClick = {
                when(processStep) {
                    1 -> {
                        processStep = 2
                        processImage = R.drawable.lemon_squeeze
                        processText = R.string.lemonStepTwoText
                    }
                    2 -> {
                        stepTwoTapCounter++
                        if (stepTwoTapCounter == stepTwoRandomNumber) {
                            processStep = 3
                            processImage = R.drawable.lemon_drink
                            processText = R.string.lemonStepThreeText
                            stepTwoTapCounter = 0
                            stepTwoRandomNumber = (2..4).random()
                        }
                        }
                    3 -> {
                        processStep = 4
                        processImage = R.drawable.lemon_restart
                        processText = R.string.lemonStepFourText
                    }
                    4 -> {
                        processStep = 1
                        processImage = R.drawable.lemon_tree
                        processText = R.string.lemonStepOneText}
                }
            },
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R
                .color.lightAqua)),
            shape = RoundedCornerShape(20)


        ) {
            Column {
                Image(
                    painter = painterResource(id = processImage),
                    contentDescription = "text"
                )
            }

        }
        Spacer(
            modifier = modifier
                .height(10.dp)
        )
        Text(
            text = stringResource(id = processText)
        )
    }
}