package com.tanmaysuryawanshi.btechproject.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tanmaysuryawanshi.btechproject.R

import com.tanmaysuryawanshi.btechproject.data.DataOrException
import com.tanmaysuryawanshi.btechproject.data.model.Weather
import com.tanmaysuryawanshi.btechproject.data.model.WeatherItem
import com.tanmaysuryawanshi.btechproject.ml.Cropsmodel
import com.tanmaysuryawanshi.btechproject.presentation.navigation.WeatherScreens
import com.tanmaysuryawanshi.btechproject.utils.formatDate
import com.tanmaysuryawanshi.btechproject.utils.formatDay
import com.tanmaysuryawanshi.btechproject.utils.formatDecimals
import com.tanmaysuryawanshi.btechproject.utils.roundAndConstrain
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?,
    stage: String
) {


    ShowData(
        navController = navController,
        mainViewModel = mainViewModel,
        city = city,
        stage = stage
    )


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowData(
    navController: NavController,
    mainViewModel: MainViewModel,
    city: String?,
    stage: String
) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = city.toString())
    }.value


    if (weatherData.loading == true) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie))

        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.cream))
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .fillMaxSize(0.5f),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "AgriShield",
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.boxgstart)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Crop Disease Prediction and Cure",
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light,
                    color = colorResource(id = R.color.blacktitle),
                    textAlign = TextAlign.Center
                )
            }


        }

    } else if (weatherData.data != null) {
        var nightTempZone = 1
        when (weatherData.data!!.list[0].temp.night.toInt()) {
            in 0..13 -> nightTempZone = 3
            in 14..17 -> nightTempZone = 2
            in 18..25 -> nightTempZone = 1
            in 26..35 -> nightTempZone = 2
            in 36..39 -> nightTempZone = 3
            else -> nightTempZone = 4
        }
        var dayTempZone = 1
        when (weatherData.data!!.list[0].temp.day.toInt()) {
            in 0..9 -> dayTempZone = 2
            in 10..20 -> dayTempZone = 1
            in 21..25 -> dayTempZone = 3
            else -> dayTempZone = 4
        }


        var humidityZone = 3;
        when (weatherData.data!!.list[0].humidity.toInt()) {
            in 0..40 -> humidityZone = 4
            in 41..70 -> humidityZone = 2
            else -> humidityZone = 1


        }

        var growthStage = stage.trim().toInt();
        var growthStageZone = 3;
        when (growthStage) {
            in 0..40 -> growthStageZone = 1
            in 41..65 -> growthStageZone = 2
            in 65..90 -> growthStageZone = 3
            else -> growthStageZone = 4

        }

        val inputFeatures = FloatArray(4)
        inputFeatures[0] = humidityZone.toFloat();//humidity zone
        inputFeatures[2] = dayTempZone.toFloat();//day temp zone
        inputFeatures[3] = nightTempZone.toFloat();//night temp zone
        inputFeatures[1] = growthStageZone.toFloat();//crop growth zone
        val model = Cropsmodel.newInstance(LocalContext.current)

// Create a ByteBuffer and load the inputFeatures into it
        val byteBuffer = ByteBuffer.allocate(inputFeatures.size * 4) // 4 bytes per float
        byteBuffer.order(ByteOrder.nativeOrder())
        for (inputFeature in inputFeatures) {
            byteBuffer.putFloat(inputFeature)
        }
        byteBuffer.rewind()

// Create the TensorBuffer and load the ByteBuffer into it
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 4), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

// Run model inference and get the result
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

// Access the output values
        val outputValues = outputFeature0.floatArray[0]

        model.close()
        val constraint = roundAndConstrain(outputValues, 1, 4)
        var riskOutput = ""
        when (constraint) {
            1 -> riskOutput = "High Risk "
            2 -> riskOutput = "Moderate Risk"
            3 -> riskOutput = "Low Risk"
            4 -> riskOutput = "No Risk"
        }


        WeatherScreen(navController, weatherData.data!!, riskOutput)


    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(navController: NavController, data: Weather, riskOutput: String) {

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xF6f6f6),
            Color(0xCDBDE3AC),
            Color(0x0094D048)
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
    ) {

        Scaffold()

        {
        Column(
            modifier = Modifier.background(gradientBrush)
                .fillMaxWidth()
        ) {
            weatherDetails(navController, data)

            Box(
                modifier = Modifier
                    .width(230.dp)

                    .padding(16.dp)
                    .background(gradientBrush)
            ) {
                var text = remember {
                    mutableStateOf("")
                }
                val daytemp = data.list[0].feelsLike.day
                val nightTemp = data.list[0].feelsLike.night
                val humidity = data.list[0].humidity
                val cloudiness = data.list[0].clouds

                text.value = riskOutput

                Text(
                    text = text.value,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(gradientBrush)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(800),
                        color = Color(0xFF3E552C),

                        )
                )

            }


        }

    }
    }


}

@Composable
private fun weatherColumnItem(weatherItem: WeatherItem) {

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xF6f6f6),
            Color(0xCDBDE3AC),
            Color(0x0094D048)
        )
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier

            .padding(start = 16.dp)
            .clip(RoundedCornerShape(16.dp))

            .background(gradientBrush)


    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = formatDecimals(weatherItem.temp.day),
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            color = colorResource(id = R.color.blacktitle)
        )
        val imageItemUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
        Image(
            painter = rememberAsyncImagePainter(imageItemUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(50.dp)
        )

        Text(
            text = formatDay(weatherItem.dt), fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
            fontFamily = FontFamily.SansSerif,

            color = Color(0x80000000)
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun weatherDetails(
    navController: NavController,
    data: Weather
) {


    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (section1, section2) = createRefs()


        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp))
                .border(width = 1.dp, Color(0xBFffffff))
                .padding(1.dp)
                .background(color =   Color(0xffA9D699))

                .constrainAs(section1) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)

                }
        ) {

            Column() {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_grid_view_24),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(18.dp)
                            .size(28.dp)
                            .clickable {
                                // navDrawerState.value = !navDrawerState.value
                            },
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = data.city.name, modifier = Modifier
                            .padding(16.dp)
                            .clickable { navController.navigate(WeatherScreens.SearchScreen.route) },

                        fontFamily = FontFamily.SansSerif,

                        style = TextStyle(
                            fontSize = 25.sp,

                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),

                            )


                    )
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(18.dp)
                            .border(2.dp, shape = CircleShape, color = Color.White)
                            .padding(2.dp)
                            .size(32.dp)

                            .clip(CircleShape)
                    )


                }


                Spacer(modifier = Modifier.height(16.dp))

                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

                    val (box1, box2, box3) = createRefs()


                    Box(

                        modifier = Modifier

                            .clip(RoundedCornerShape(28.dp))
                            .size(200.dp)
                            .background(

Color(0xff445D48)
                            )

                            .constrainAs(box2) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Text(
                                text = data.list[0].weather[0].main,
                                modifier = Modifier.padding(top = 8.dp),
                                fontSize = 16.sp,
                                fontFamily = FontFamily.SansSerif,

                                color = Color.White
                            )

                            Text(
                                text = formatDecimals(data.list[0].temp.day) + "\u00B0C",
                                modifier = Modifier
                                    .padding(16.dp),
                                fontSize = 65.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                        }
                    }

                    Image(painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)

                            .constrainAs(box3) {
                                start.linkTo(box2.start)
                                end.linkTo(box2.end)
                                top.linkTo(box2.bottom)
                                bottom.linkTo(box2.bottom)
                            })


                    Text(
                        text = formatDate(data.list[0].dt), modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(color = Color.White)
                            .padding(vertical=8.dp, horizontal = 16.dp)
                            .constrainAs(box1) {
                                start.linkTo(box2.start)
                                end.linkTo(box2.end)
                                top.linkTo(box2.top)
                                bottom.linkTo(box2.top)
                            },
                        fontSize = 17.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.blacktitle)
                    )


                }

                Spacer(modifier = Modifier.height(72.dp))
            }


        }


        Box(
            modifier = Modifier

                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp))
                .fillMaxWidth(0.8f)
                .shadow(elevation = 100.dp)
                .background(color = Color.White)

                .constrainAs(section2) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(section1.bottom)
                    bottom.linkTo(section1.bottom)
                }

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp,
                        horizontal = 8.dp
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.wind_icon),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.boxgend))
                    )
                    Text(
                        text = data.list[0].speed.toString(),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.blacktitle)
                    )
                    Text(
                        text = "Wind", fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = colorResource(id = R.color.blacktitle)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.material_symbols_humidity_low),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.boxgend))
                    )
                    Text(
                        text = data.list[0].humidity.toString(),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.blacktitle)
                    )
                    Text(
                        text = "Humidity", fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = colorResource(id = R.color.blacktitle)
                    )
                }


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.mdi_visibility_outline),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.boxgend))
                    )
                    Text(
                        text = data.list[0].clouds.toString(),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.blacktitle)
                    )
                    Text(
                        text = "Visibility", fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = colorResource(id = R.color.blacktitle)
                    )
                }


            }
        }

    }
}

@Preview
@Composable
fun appPreview() {
    // WeatherScreen(weatherData)
}
