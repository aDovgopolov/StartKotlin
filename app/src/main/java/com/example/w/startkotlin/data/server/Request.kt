package com.example.w.startkotlin.data.server

import android.util.Log
import java.net.URL

/**
 * Created by W on 05.07.2018.
 */
class Request(val url: String) {

    fun run(){
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }

}