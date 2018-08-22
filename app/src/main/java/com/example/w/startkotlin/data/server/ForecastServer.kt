package com.example.w.startkotlin.data.server

import com.example.w.startkotlin.domain.datasource.ForecastDataSource
import com.example.w.startkotlin.data.db.ForecastDb
import com.example.w.startkotlin.domain.model.ForecastList
import java.lang.UnsupportedOperationException

class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()): ForecastDataSource {

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }


}