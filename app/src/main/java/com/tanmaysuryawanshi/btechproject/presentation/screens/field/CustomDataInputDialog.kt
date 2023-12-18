package com.tanmaysuryawanshi.btechproject.presentation.screens.field

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.tanmaysuryawanshi.btechproject.domain.model.Fields

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDataInputDialog(
    fields: Fields,
    onDismiss: () -> Unit,
    onUpdateData: (Fields) -> Unit
) {
    var fieldName = remember { mutableStateOf(fields.fieldName) }
    var cropGrowthStage = remember { mutableStateOf(fields.cropGrowthStage) }
    var place = remember { mutableStateOf(fields.place) }
    val verticalGradientBrushDialogBox = Brush.verticalGradient(
        colors = listOf(
            Color(0xA1F5E4B7),
            Color(0x4FA9DA8C),
            Color(0x61F5E4B7),
        )
    )

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .background(Color.Transparent),
            shape = RoundedCornerShape(26.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(verticalGradientBrushDialogBox, shape = RoundedCornerShape(26.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                Text(
                    text = "Enter Field Details",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        fontSize = 22.sp,

                        color = Color(0xFF3B6542),

                        )
                )
                Spacer(modifier = Modifier.height(16.dp))
                var isFieldNameSelected = remember { mutableStateOf(false) }
                var isPlaceNameSelected = remember { mutableStateOf(false) }
                var isGrowthStageSelected = remember { mutableStateOf(false) }
                TextField(

                    value = fieldName.value,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x1469FC24),
                        focusedIndicatorColor = Color.Transparent, // Remove the underline on focus
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0x4D000000),
                            shape = RectangleShape
                        )
                        .onFocusChanged { focusState ->
                            isFieldNameSelected.value = focusState.isFocused
                        },
                    onValueChange = { fieldName.value = it },
                    label = {
                        if (!isFieldNameSelected.value) {
                            Text(
                                "Field Name", style = TextStyle(
                                    fontSize = 15.sp,

                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF000000),

                                    )
                            )
                        }
                    })

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = cropGrowthStage.value,
                    onValueChange = { cropGrowthStage.value = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x1469FC24),
                        focusedIndicatorColor = Color.Transparent, // Remove the underline on focus
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0x4D000000),
                            shape = RectangleShape
                        )
                        .onFocusChanged { focusState ->
                            isGrowthStageSelected.value = focusState.isFocused
                        },
                    label = {
                        if (!isGrowthStageSelected.value) {
                            Text(
                                "Crop Growth Stage", style = TextStyle(
                                    fontSize = 15.sp,

                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF000000),

                                    )
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = place.value,
                    onValueChange = { place.value = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x1469FC24),
                        focusedIndicatorColor = Color.Transparent, // Remove the underline on focus
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0x4D000000),
                            shape = RectangleShape
                        )
                        .onFocusChanged { focusState ->
                            isPlaceNameSelected.value = focusState.isFocused
                        },
                    label = {
                        if (!isPlaceNameSelected.value) {
                            Text(
                                "Location", style = TextStyle(
                                    fontSize = 15.sp,

                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF000000),

                                    )
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xff70B236)),
                    onClick = {
                        val updatedFields = Fields(
                            fieldName = fieldName.value,
                            cropGrowthStage = cropGrowthStage.value,
                            place = place.value
                        )
                        onUpdateData(updatedFields)

                    }
                ) {
                    Text(
                        text = "Add",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF),
                            shadow = Shadow(color = Color.Gray, blurRadius = 3f, offset = Offset(1f,1f))
                        )
                    )
                }
            }
        }
    }
}
