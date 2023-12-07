package com.tanmaysuryawanshi.btechproject.data.model


import com.google.gson.annotations.SerializedName
import com.tanmaysuryawanshi.btechproject.data.model.FeelsLike
import com.tanmaysuryawanshi.btechproject.data.model.Temp
import com.tanmaysuryawanshi.btechproject.data.model.WeatherObject

data class WeatherItem(
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike,
    @SerializedName("gust")
    val gust: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("temp")
    val temp: Temp,

    @SerializedName("weather")
    val weather: List<WeatherObject>
)