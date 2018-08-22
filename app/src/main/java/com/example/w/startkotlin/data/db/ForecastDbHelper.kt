package com.example.w.startkotlin.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.w.startkotlin.ui.App
import org.jetbrains.anko.db.*

class ForecastDbHelper(ctx: Context = App.instance): ManagedSQLiteOpenHelper(ctx,
        ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Tables.CityForecastTable.name, true)
        db.dropTable(Tables.DayForecastTable.name, true)
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(Tables.CityForecastTable.name, true,
            Tables.CityForecastTable.ID to INTEGER + PRIMARY_KEY,
            Tables.CityForecastTable.CITY to TEXT,
            Tables.CityForecastTable.country to TEXT)

        db.createTable(Tables.DayForecastTable.name, true,
                Tables.DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Tables.DayForecastTable.DATE to INTEGER,
                Tables.DayForecastTable.DESCRIPTION to TEXT,
                Tables.DayForecastTable.HIGH to INTEGER,
                Tables.DayForecastTable.LOW to INTEGER,
                Tables.DayForecastTable.ICON_URL to TEXT,
                Tables.DayForecastTable.CITY_ID to INTEGER)
    }

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

}