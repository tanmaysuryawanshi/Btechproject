package com.tanmaysuryawanshi.btechproject.domain.model

data class DataFarmers (
        val userId: String="",
        val username: String="",
        val profilePictureUrl: String="",


        var fieldList:List<Fields> = emptyList()

        )

data class Fields(
        var fieldName:String="",
        var cropGrowthStage:String="",
        var place:String="pune"

)