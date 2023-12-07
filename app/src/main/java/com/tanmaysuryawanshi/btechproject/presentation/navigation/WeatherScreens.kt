package com.tanmaysuryawanshi.btechproject.presentation.navigation

sealed class WeatherScreens(val route:String) {

    object SplashScreen: WeatherScreens("spash_screen")
    object MainScreen: WeatherScreens("main_screen")

    object SearchScreen: WeatherScreens("search_screen")
    object LoginScreen: WeatherScreens("login_screen")
    object FieldScreen: WeatherScreens("field_screen")

}