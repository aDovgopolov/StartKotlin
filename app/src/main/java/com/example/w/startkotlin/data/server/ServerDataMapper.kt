package com.example.w.startkotlin.data.server

import com.example.w.startkotlin.domain.model.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import com.example.w.startkotlin.domain.model.Forecast as ModelForecast
//import com.antonioleiva.weatherapp.domain.model.Forecast as ModelForecast

class ServerDataMapper {

//    fun convertFromDataModel(zipCode: Long, forecast: ForecastResult) = with(forecast) {
//        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
//    }

    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<com.example.w.startkotlin.domain.model.Forecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(-1, dt, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String =
            "http://openweathermap.org/img/w/$iconCode.png"

}