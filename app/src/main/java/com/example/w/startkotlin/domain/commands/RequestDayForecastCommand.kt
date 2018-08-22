package com.example.w.startkotlin.domain.commands

import com.example.w.startkotlin.domain.datasource.ForecastProvider
import com.example.w.startkotlin.domain.model.Forecast

class RequestDayForecastCommand(
        val id: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}