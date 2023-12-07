package com.tanmaysuryawanshi.btechproject.domain.repository


import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.tanmaysuryawanshi.btechproject.domain.model.DataFarmers
import com.tanmaysuryawanshi.btechproject.utils.Constants.COLLECTION_FARM_DATA
import com.tanmaysuryawanshi.btechproject.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

import javax.inject.Inject

class FarmerDataRepositoryImpl @Inject constructor(val firebaseFirestore: FirebaseFirestore):FarmerDataRepository {
    override fun getFarmerdata(userId: String): Flow<Resource<DataFarmers>> = flow{


        val data=firebaseFirestore.collection(COLLECTION_FARM_DATA).document(userId).get()


    }
}