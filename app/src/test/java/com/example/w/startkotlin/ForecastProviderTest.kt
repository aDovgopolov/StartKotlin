package com.example.w.startkotlin

import com.example.w.startkotlin.domain.datasource.ForecastDataSource
import com.example.w.startkotlin.domain.datasource.ForecastProvider
import com.example.w.startkotlin.domain.model.Forecast
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.*

class ForecastProviderTest{

    @Test fun testDataSourceReturnsValue() {
        val ds = mock(ForecastDataSource::class.java)
        `when`(ds.requestDayForecast(0)).then { Forecast(0, 0, "desc", 20, 0, "url") }

        val provider = ForecastProvider(listOf(ds))
        assertNotNull(provider.requestForecast(0))
    }
}