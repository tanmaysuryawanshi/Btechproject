package com.tanmaysuryawanshi.btechproject.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.tanmaysuryawanshi.btechproject.data.DataOrException
import com.tanmaysuryawanshi.btechproject.data.model.Weather
import com.tanmaysuryawanshi.btechproject.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository)
    :ViewModel() {
    suspend fun getWeatherData(city: String):
            DataOrException<Weather, Boolean, Exception> {
       return repository.getWeather(cityQuery = city)
    }
}




