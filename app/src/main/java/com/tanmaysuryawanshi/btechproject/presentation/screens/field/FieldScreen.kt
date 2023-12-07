package com.tanmaysuryawanshi.btechproject.presentation.screens.field

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.tanmaysuryawanshi.btechproject.R
import com.tanmaysuryawanshi.btechproject.domain.model.Fields
import com.tanmaysuryawanshi.btechproject.presentation.navigation.WeatherScreens
import kotlinx.coroutines.coroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldScreen(fieldViewmodel: FieldViewmodel, navController: NavController) {
    var isDialogVisible = remember { mutableStateOf(false) }


    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(text = "Your fields")
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
isDialogVisible.value=true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = colorResource(
                        id = R.color.boxgstart
                    )
                )
            }
        }
    ) {

        if (isDialogVisible.value) {
            val field = Fields()
            CustomDataInputDialog(
                fields = field,
                onDismiss = { isDialogVisible.value = false },
                onUpdateData =
                {fieldsit->
                    val fieldNew=fieldViewmodel.state.value
                    val updatedFieldList = fieldViewmodel.state.value.fieldList.toMutableList()
                    updatedFieldList.add(fieldsit)
                   fieldNew.fieldList=updatedFieldList
                    Log.d("FieldScreen", "FieldScreen: update function call")
                        fieldViewmodel.updateDataFarmers(fieldNew)
                            isDialogVisible.value=false
                    Log.d("FieldScreen", "FieldScreen: update function call complete")

                }
            )
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            val data = fieldViewmodel.state.value


            items(data.fieldList) {
                Card(
                    modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth()
                        .clickable { navController.navigate(WeatherScreens.MainScreen.route + "/" + it.place+ "/" +it.cropGrowthStage) }
                        .padding(10.dp)
                        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)),

                    ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .background(color = Color.White)
                            .padding(8.dp)
                    ) {

                        Text(
                            text = it.fieldName,
                            fontSize = 24.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()

                                .background(Color.White)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Place,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.CenterVertically),
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = it.place,
                                fontSize = 18.sp,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                fontWeight = FontWeight.Light
                            )
                        }

                    }
                }
            }

        }

    }
}