package com.example.w.startkotlin.data.db

import com.example.w.startkotlin.domain.datasource.ForecastDataSource
import com.example.w.startkotlin.domain.model.Forecast
import com.example.w.startkotlin.domain.model.ForecastList
import com.example.w.startkotlin.extensions.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.HashMap

class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

        val dailyRequest = "${Tables.DayForecastTable.CITY_ID} = ? AND ${Tables.DayForecastTable.DATE} >= ?"

        val dailyForecast = select(Tables.DayForecastTable.name)
                //.where(dailyRequest, "id" to zipCode, "date" to date)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList{DayForecast(HashMap(it))}

        val city = select(Tables.CityForecastTable.name)
                .whereSimple("${Tables.CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt{CityForecast(HashMap(it), dailyForecast)}

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    override fun requestDayForecast(id: Long) = forecastDbHelper.use {
        val forecast = select(Tables.DayForecastTable.name).byId(id).
                parseOpt { DayForecast(HashMap(it)) }

        if (forecast != null) dataMapper.convertDayToDomain(forecast) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {

        clear(Tables.CityForecastTable.name)
        clear(Tables.DayForecastTable.name)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(Tables.CityForecastTable.name, *map.toVarargArray())
            dailyForecast.forEach { insert(Tables.DayForecastTable.name, *it.map.toVarargArray()) }
        }
    }
}