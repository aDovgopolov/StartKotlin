package com.example.w.startkotlin.data.db

/**
 * Created by W on 10.07.2018.
 */
class Tables {

    object CityForecastTable{
        val name = "CityForecast"
        val ID = "_id"
        val CITY = "city"
        val country = "country"
    }

    object DayForecastTable{
        val name = "DayForecast"
        val ID = "_id"
        val DATE = "date"
        val DESCRIPTION = "description"
        val HIGH = "high"
        val LOW = "low"
        val ICON_URL = "iconUrl"
        val CITY_ID = "cityId"
    }

}