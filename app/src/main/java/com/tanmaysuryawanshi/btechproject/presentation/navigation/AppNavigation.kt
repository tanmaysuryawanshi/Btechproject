package com.tanmaysuryawanshi.btechproject.presentation.navigation

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.identity.Identity
import com.tanmaysuryawanshi.btechproject.presentation.screens.field.FieldScreen
import com.tanmaysuryawanshi.btechproject.presentation.screens.field.FieldViewmodel
import com.tanmaysuryawanshi.btechproject.presentation.screens.main.MainScreen
import com.tanmaysuryawanshi.btechproject.presentation.screens.main.MainViewModel
import com.tanmaysuryawanshi.btechproject.presentation.screens.search.SearchScreen
import com.tanmaysuryawanshi.btechproject.presentation.screens.signin.GoogleAuthUiClient
import com.tanmaysuryawanshi.btechproject.presentation.screens.signin.LoginScreen
import com.tanmaysuryawanshi.btechproject.presentation.screens.signin.SignInViewModel
import com.tanmaysuryawanshi.btechproject.presentation.screens.splash.SplashScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val applicationContext = LocalContext.current
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    NavHost(
        navController = navController,
        startDestination = WeatherScreens.LoginScreen.route
    ) {
        composable(WeatherScreens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(WeatherScreens.FieldScreen.route) {
            val fieldViewModel = hiltViewModel<FieldViewmodel>()
            FieldScreen(fieldViewmodel = fieldViewModel, navController = navController)
        }
        composable(WeatherScreens.LoginScreen.route) {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsState()

            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignedInUser() != null) {
                  //   navController.navigate(WeatherScreens.MainScreen.route + "/pune/10")
                    navController.navigate(WeatherScreens.FieldScreen.route)
                    {
                        popUpTo(WeatherScreens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }


            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

                   // navController.navigate(WeatherScreens.MainScreen.route + "/pune")
                    navController.navigate(WeatherScreens.FieldScreen.route)
                    {
                        popUpTo(WeatherScreens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                    viewModel.resetState()
                }
            }
            LoginScreen(navController, state,

                onSignInClick = {
                    Log.d("login", "AppNavigation: button clicked")
                    lifecycleScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )
        }

        composable(WeatherScreens.SearchScreen.route) {
            SearchScreen(navController)
        }
        composable(
            WeatherScreens.MainScreen.route + "/{city}/{stage}",
            arguments = listOf(
                navArgument("city") {
                    type = NavType.StringType
                    defaultValue = "pune"
                },
                navArgument("stage") {
                    type = NavType.StringType
                    defaultValue = "10"
                }
            )
        ) { it ->
            val stageArg=it.arguments?.getString("stage")
            it.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                if (stageArg != null) {
                    MainScreen(navController = navController, mainViewModel, city = city, stage =stageArg )
                }
            }
        }
    }
}