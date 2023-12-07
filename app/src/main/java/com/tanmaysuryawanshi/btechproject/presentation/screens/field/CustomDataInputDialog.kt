package com.tanmaysuryawanshi.btechproject.presentation.screens.field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Enter Data", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))

                TextField(   value = fieldName.value,
                    onValueChange = { fieldName.value = it },
                    label = { Text("Field Name") })

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = cropGrowthStage.value,
                    onValueChange = { cropGrowthStage.value = it },
                    label = { Text("Crop Growth Stage") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = place.value,
                    onValueChange = { place.value = it },
                    label = { Text("Place") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val updatedFields = Fields(fieldName = fieldName.value, cropGrowthStage = cropGrowthStage.value,place= place.value)
                        onUpdateData(updatedFields)

                    }
                ) {
                    Text(text = "Update Data")
                }
            }
        }
    }
}
