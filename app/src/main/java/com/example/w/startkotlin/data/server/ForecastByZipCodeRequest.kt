package com.example.w.startkotlin.data.server
import com.google.gson.Gson
import java.net.URL

class ForecastByZipCodeRequest(val zipCode: Long) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "${URL}&APPID=${APP_ID}&q="
        }

    fun execute(): ForecastResult {
        //val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
           //     "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"
        val forecastjsonStr = URL(COMPLETE_URL + zipCode).readText()
        //val forecastjsonStr = URL(url).readText()
        return Gson().fromJson(forecastjsonStr, ForecastResult::class.java)
    }
}