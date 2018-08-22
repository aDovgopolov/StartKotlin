package com.example.w.startkotlin.domain.datasource

import com.example.w.startkotlin.domain.model.Forecast
import com.example.w.startkotlin.domain.model.ForecastList

interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}