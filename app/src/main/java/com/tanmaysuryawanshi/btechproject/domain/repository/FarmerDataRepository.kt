package com.tanmaysuryawanshi.btechproject.domain.repository


import com.tanmaysuryawanshi.btechproject.domain.model.DataFarmers
import com.tanmaysuryawanshi.btechproject.utils.Resource
import kotlinx.coroutines.flow.Flow


interface FarmerDataRepository {
    fun getFarmerdata(userId:String):Flow<Resource<DataFarmers>>
}