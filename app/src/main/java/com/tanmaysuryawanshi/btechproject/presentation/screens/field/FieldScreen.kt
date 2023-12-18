package com.tanmaysuryawanshi.btechproject.presentation.screens.field

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tanmaysuryawanshi.btechproject.R
import com.tanmaysuryawanshi.btechproject.domain.model.Fields
import com.tanmaysuryawanshi.btechproject.presentation.navigation.WeatherScreens
import kotlinx.coroutines.Job


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldScreen(fieldViewmodel: FieldViewmodel, navController: NavController, signout: () -> Unit) {
    var isDialogVisible = remember { mutableStateOf(false) }
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0x2DEB34),
            Color(0xB5FF88),
            Color(0x01ED51),
            Color(0xF5F6AA)
        ),
    )



    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(title = {

                },
                    actions = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(13.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(0.dp)
                                    .width(53.dp)
                                    .height(59.dp), alignment = Alignment.TopCenter,
                                contentScale = ContentScale.Crop,
                                alpha = 0.8f,
                                colorFilter = ColorFilter.tint(
                                    Color(0xF2294C07),
                                    blendMode = BlendMode.SrcAtop
                                )


                            )

                            Text(
                                text = "Fields Names",
                                style = TextStyle(
                                    fontSize = 25.sp,

                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF294C07),

                                    )
                            )
                            Button(
                                onClick =signout
                                ,
                                shape = RoundedCornerShape(size = 7.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFF517631
                                    )
                                )
                            ) {
                                Text(
                                    text = "Logout", style = TextStyle(
                                        fontSize = 16.sp,

                                        fontWeight = FontWeight(400),
                                        color = Color(0xFFFFFFFF),

                                        )
                                )
                            }
                        }
                    }

                )
                Divider(color = Color(0x66000000))
            }
        },
        floatingActionButton = {
            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxWidth()) {
                FloatingActionButton(
                    onClick = {
                        isDialogVisible.value = true
                    },

                    ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color(0xff57805E)
                    )
                }
            }
        }
    ) {

        if (isDialogVisible.value) {
            val field = Fields()
            CustomDataInputDialog(
                fields = field,
                onDismiss = { isDialogVisible.value = false },
                onUpdateData =
                { fieldsit ->
                    val fieldNew = fieldViewmodel.state.value
                    val updatedFieldList = fieldViewmodel.state.value.fieldList.toMutableList()
                    updatedFieldList.add(fieldsit)
                    fieldNew.fieldList = updatedFieldList
                    Log.d("FieldScreen", "FieldScreen: update function call")
                    fieldViewmodel.updateDataFarmers(fieldNew)
                    isDialogVisible.value = false
                    Log.d("FieldScreen", "FieldScreen: update function call complete")

                }
            )
        }
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(

                            Color(0xFFF6F6F6), // #F6F6F6
                            Color(0xCDBDE3AC), // #BDE3ACCD

                        )
                    )

                )
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(
//                        Brush.linearGradient(
//                            colors = listOf(
//                                Color(0xFFF6F6F6), // #F6F6F6
//                                Color(0xCDBDE3AC), // #BDE3ACCD
//                                Color(0x0094D048)  // #94D04800
//                            ),
//                            start = Offset(0f, 0f),
//                            end = Offset(1f, 0f)
//                        )
//                    )
//                    .padding(it)
            ) {
                val data = fieldViewmodel.state.value


                items(data.fieldList) {
                    Card(
                        modifier = Modifier

                            .fillMaxWidth()
                            .clickable { navController.navigate(WeatherScreens.MainScreen.route + "/" + it.place + "/" + it.cropGrowthStage) }
                            .padding(start = 22.dp, end = 22.dp, top = 22.dp)
                            .border(
                                1.dp,
                                color = Color(0x5B5B5B99),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(Color.White)
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFFF7F9D1),
                                        Color(0xFFBDE3AC),

                                        )
                                )
                            ),

                        ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .background(Color.White)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFFF7F9D1),
                                            Color(0x7ABDE3AC),

                                            )
                                    )
                                )
                                .padding(8.dp)
                        ) {

                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.Bottom

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.wheaticon),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(33.dp)
                                        .width(30.dp)
                                )
                                Text(
                                    text = it.fieldName,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E5631),
                                    fontSize = 22.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.Bottom

                            ) {

                                Spacer(modifier = Modifier.width(38.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.calender),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(29.dp)
                                        .height(27.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Days After Pruning",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xCC000000),

                                        )
                                )
                                Spacer(modifier = Modifier.width(40.dp))
                                Box(
                                    modifier = Modifier
                                        .border(
                                            color = Color(0x1A000000),
                                            shape = RoundedCornerShape(size = 31.dp), width = 1.dp
                                        )
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(

//                                        Color(0x0049C934),
//                                        Color(0x749C934),
//                                        Color(0x0049C934),

                                                    Color(0xFFF7F9D1),
                                                    Color(0xB0BDE3AC),

                                                    )
                                            ), shape = RoundedCornerShape(size = 31.dp)
                                        )


                                ) {
                                    Text(
                                        text = it.cropGrowthStage + " Days",

                                        style = TextStyle(
                                            //  fontFamily = FontFamily(Font(R.font.patua)),
                                            //fontWeight = FontWeight(400),
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xCC000000),

                                            ),
                                        modifier = Modifier.padding(
                                            vertical = 4.dp,
                                            horizontal = 12.dp
                                        ),
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.Bottom

                            ) {

                                Spacer(modifier = Modifier.width(38.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.location),
                                    contentDescription = null,
                                    modifier = Modifier.size(25.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(
                                    modifier = Modifier
                                        .border(
                                            color = Color(0x1A000000),
                                            shape = RoundedCornerShape(size = 31.dp), width = 1.dp
                                        )
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(

//                                        Color(0x0049C934),
//                                        Color(0x749C934),
//                                        Color(0x0049C934),

                                                    Color(0xFFF7F9D1),
                                                    Color(0xB0BDE3AC),

                                                    )
                                            ), shape = RoundedCornerShape(size = 31.dp)
                                        )


                                ) {
                                    Text(
                                        text = it.place,
                                        fontSize = 13.sp,
                                        color = Color(0xCC000000),

                                        modifier = Modifier.padding(
                                            horizontal = 30.dp,
                                            vertical = 4.dp
                                        ),
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(28.dp))
                        }
                    }
                }
item { Spacer(modifier = Modifier.height(60.dp)) }
            }
        }

    }
}