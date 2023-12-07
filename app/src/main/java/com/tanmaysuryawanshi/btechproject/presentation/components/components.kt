package com.tanmaysuryawanshi.btechproject.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter



@Composable
fun weatherUrltoImage(url:String,modifier: Modifier) {
    Image(painter = rememberAsyncImagePainter(url), contentDescription = null
    ,modifier= Modifier)
}