package com.tanmaysuryawanshi.btechproject.data.network

import com.tanmaysuryawanshi.btechproject.data.model.Weather
import com.tanmaysuryawanshi.btechproject.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units" )units:String="metric",
        @Query("appid") appid:String = Constants.Api_Key
    ): Weather

}