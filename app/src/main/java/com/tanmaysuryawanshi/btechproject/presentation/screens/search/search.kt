package com.tanmaysuryawanshi.btechproject.presentation.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tanmaysuryawanshi.btechproject.presentation.navigation.WeatherScreens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navController: NavHostController) {

    val searchQueryState= rememberSaveable {
        mutableStateOf("")
    }
    val valid=remember(searchQueryState.value){
        searchQueryState.value.trim().isNotEmpty()
    }
val keyboardController=LocalSoftwareKeyboardController.current
    OutlinedTextField(value = searchQueryState.value,
    onValueChange ={
        searchQueryState.value=it
    },
    modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
  maxLines = 1,
    singleLine = true,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text
    , imeAction = ImeAction.Next),
    keyboardActions = KeyboardActions {
        if (!valid){
         return@KeyboardActions
        }
        else {

            navController.navigate(WeatherScreens.MainScreen.route + "/" + searchQueryState.value)
      keyboardController?.hide()
        }
    }
)

}