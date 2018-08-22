package com.example.w.startkotlin.ui

import android.app.Application
import com.example.w.startkotlin.extensions.DelegatesExt

class App : Application(){

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()

    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}