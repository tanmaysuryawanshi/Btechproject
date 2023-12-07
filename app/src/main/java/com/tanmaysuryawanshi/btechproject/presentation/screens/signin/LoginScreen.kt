package com.tanmaysuryawanshi.btechproject.presentation.screens.signin

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import com.google.firebase.ktx.Firebase
import com.tanmaysuryawanshi.btechproject.R


@Composable
fun LoginScreen(navigationController: NavController,state:SignInState,
                onSignInClick: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.cream)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly){


        Image(painter = painterResource(id = R.drawable.img_4),
            contentDescription = null,
            modifier = Modifier
                .padding(0.dp, bottom = 32.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.65f)

            , alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text(text = "Agri",
                    color = colorResource(id = R.color.purple) ,
                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)




                )
                Text(text = "Shield",

                    color = colorResource(id = R.color.boxgstart),
                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)




                )
            }

            Column() {
                Row(horizontalArrangement = Arrangement.Center) {
                    Text(text = "Say goodbye to",
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center




                    )
                    Text(text = "worrying about your grapes!",
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center




                    )
                }

                Text(text = "Stay informed about the crops status",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center




                )
            }




            Button(
                onClick =onSignInClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                modifier = Modifier.padding(8.dp)

            ) {




                Image(
                    painter = painterResource(id = R.drawable.icon_google),
                    contentDescription = null
                )
                Text(
                    text = "Continue with Google",
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    modifier = Modifier.padding(8.dp)


                )
            }
        }

    }
}
