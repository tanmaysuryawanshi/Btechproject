package com.tanmaysuryawanshi.btechproject.presentation.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 64.dp),
    contentAlignment = Alignment.Center){
        Text(text = "Header")
    }
}


@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier=Modifier,
    itemTextStyle: TextStyle= TextStyle(
    fontSize = 14.sp,
    fontFamily = FontFamily.SansSerif
),
    onItemClick: (MenuItem) -> Unit
) {

    LazyColumn( modifier){

        items(items){
            item->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(item)
                }
                .padding(16.dp)) {
Icon(imageVector = item.icon, contentDescription = null)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title,
                modifier = Modifier.weight(1f),
                style = itemTextStyle)
            }
        }
    }


}

data class MenuItem(
    val id:String,
    val title:String,
    val icon:ImageVector
)