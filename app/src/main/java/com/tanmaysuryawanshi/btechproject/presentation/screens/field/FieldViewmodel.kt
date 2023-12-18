package com.tanmaysuryawanshi.btechproject.presentation.screens.field

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.tanmaysuryawanshi.btechproject.data.DataOrException
import com.tanmaysuryawanshi.btechproject.domain.model.DataFarmers
import com.tanmaysuryawanshi.btechproject.domain.repository.FarmerDataRepository
import com.tanmaysuryawanshi.btechproject.utils.Constants.COLLECTION_FARM_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FieldViewmodel @Inject constructor(
    private val db: FirebaseFirestore, private val user: FirebaseUser?
) :
    ViewModel() {
    val state = mutableStateOf(DataFarmers())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            state.value = getFieldDataFromFirestore()
        }


    }

 fun updateDataFarmers(dataFarmers: DataFarmers) {
        // Update the DataFarmers object in Firestore
     viewModelScope.launch(Dispatchers.IO) {
        try {
                val userId:String=Firebase.auth.uid?:dataFarmers.userId;
                db.collection(COLLECTION_FARM_DATA)
                    .document() // Assuming userId is used as the document ID
                    .set(dataFarmers, SetOptions.merge())
                    .addOnSuccessListener {
                        // Handle success
                        // This code is executed on a background thread
                        Log.d("FieldViewmodel", "updateDataFarmers: ")
                    }
                    .addOnFailureListener { e ->
                        // Handle failure
                        // This code is executed on a background thread
                        Log.d("FieldViewmodel", "updateDataFarmers: failed" +e.message)
                    }
            }
         catch (e: Exception) {
            // Handle exceptions, such as network issues or permission errors
        }
     }
    }

    suspend fun getFieldDataFromFirestore(): DataFarmers {
        var data = DataFarmers()

        val user = FirebaseAuth.getInstance().tenantId
        try {
            Log.d("FieldViewmodel ", "getFieldData: mapping start ")
            db.collection(COLLECTION_FARM_DATA).get().await()
                .map {
                    data = it.toObject(DataFarmers::class.java)
                    Log.d("FieldViewmodel ", "getFieldData: map " + data.toString())
                }

            Log.d("FieldViewmodel ", "getFieldData: mapping end " + data.toString())
        } catch (e: Exception) {
            Log.d("FieldViewmodel error", "getFieldData: " + e.message)
        }
        return data

    }

}

