package com.tanmaysuryawanshi.btechproject.presentation.screens.signin

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.cream)),
  ){
        Box(modifier = Modifier.blur(100.dp)) {
            Image(
                painter = painterResource(id = R.drawable.backgroundlogin),
                contentDescription = null,
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .blur(100.dp),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Box(modifier = Modifier.blur(100.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(0.dp, bottom = 32.dp)
                            .width(246.dp)
                            .height(298.dp), alignment = Alignment.TopCenter,
                        contentScale = ContentScale.Crop,
                        alpha = 0.8f,
                        colorFilter = ColorFilter.tint(
                            Color(0xF2294C07),
                            blendMode = BlendMode.SrcAtop
                        )

                    )
                }

                Text(
                    text = "Protecting Your Agricultural Dreams",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.lalezar)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF0E0E0E),

                        textAlign = TextAlign.Center,
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(painter = painterResource(id = R.drawable.ellipse), contentDescription = null)
                    Divider(
                        modifier = Modifier
                            .padding(0.dp)
                            .width(288.00174.dp)
                            .height(1.dp)
                            .background(color = Color(0xFF000000))
                        ,color=Color(0xFF000000)
                    )
                    Icon(painter = painterResource(id = R.drawable.ellipse), contentDescription = null)
                }

            }
//            Row() {
//                Text(text = "Agri",
//                    color = colorResource(id = R.color.purple) ,
//                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(bottom = 16.dp)
//
//
//
//
//                )
//                Text(text = "Shield",
//
//                    color = colorResource(id = R.color.boxgstart),
//                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(bottom = 16.dp)
//
//
//
//
//                )
//            }






            Button(
                onClick =onSignInClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xA8FFFFFF)),
                border = BorderStroke(1.dp, color = Color(0x75000000)),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x1A000000),
                        ambientColor = Color(0x1A000000)
                    )

                ,
shape = RectangleShape
            ) {




                Image(
                    painter = painterResource(id = R.drawable.icon_google),
                    contentDescription = null
                )
                Text(
                    text = "Login with Google",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.inknutantiqua)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(horizontal = 8.dp)


                )

            }
        }

    }
}
