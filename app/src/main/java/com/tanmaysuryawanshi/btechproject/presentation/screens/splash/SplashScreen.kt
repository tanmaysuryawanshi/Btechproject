package com.tanmaysuryawanshi.btechproject.presentation.screens.splash

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tanmaysuryawanshi.btechproject.R
import com.tanmaysuryawanshi.btechproject.presentation.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

  val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie))

  Box(modifier = Modifier
    .background(colorResource(id = R.color.cream))
    .fillMaxSize()){
    Column(horizontalAlignment = Alignment.CenterHorizontally
      , verticalArrangement = Arrangement.Center,
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      LottieAnimation(
        modifier = Modifier
          .fillMaxSize(0.5f)
        , composition =composition , iterations = LottieConstants.IterateForever,
        contentScale = ContentScale.Crop)

      Text(text = "AgriShield",
        fontSize = 32.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        color = colorResource(id = R.color.boxgstart)
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = "Crop Disease Prediction and Cure",
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Light,
        color = colorResource(id = R.color.blacktitle),
        textAlign = TextAlign.Center
      )
    }
  }

  navController.navigate(WeatherScreens.LoginScreen.route){
    popUpTo(WeatherScreens.SplashScreen.route) {
      inclusive = true
    }
  }
}