package com.tanmaysuryawanshi.btechproject.domain.repository

import com.tanmaysuryawanshi.btechproject.data.DataOrException
import com.tanmaysuryawanshi.btechproject.data.model.Weather
import com.tanmaysuryawanshi.btechproject.data.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi){

    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response=try {
api.getWeather(query = cityQuery)
        }catch (e:Exception){
            return DataOrException(e=e)
        }
return DataOrException(data = response)
    }

}