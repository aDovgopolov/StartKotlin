package com.example.w.startkotlin.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.example.w.startkotlin.ui.adapters.ForecastListAdapter
import com.example.w.startkotlin.R
import com.example.w.startkotlin.domain.commands.RequestForecastCommand
import com.example.w.startkotlin.domain.model.ForecastList
import com.example.w.startkotlin.extensions.DelegatesExt
import com.example.w.startkotlin.ui.ToolbarManager
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE,
             SettingsActivity.ZIP_DEFAULT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecastList.layoutManager =  LinearLayoutManager(this)
        attachToScroll(forecastList)


        //forecastList.adapter = ForecastListAdapter(items)



        //val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
        //       "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

//        doAsync() {
//            Log.d("TAG", url)
//            Request(url).run()
//            uiThread { longToast("Request performed") }
//
//        }



//        val list = arrayListOf("10","11", "1001");
//        for((index, elemet) in list.withIndex()){
//            println("$index : $elemet")
//        }
//
//        val binaryReps = TreeMap<Char, String>()
//        for(c in 'A'..'F'){
//            val binary = Integer.toBinaryString(c.toInt())
//            binaryReps[c] = binary
//        }
//
//        for(( binary, letter) in binaryReps){
//            //println("$letter = $binary")
//        }
//        //println(mix(Color.BLUE, Color.YELLOW))
//
//        val list1 = listOf(1,2,3)
//        println( list1.joinToString())
//
//        val list2 = listOf("a", "ab", "b")
//        println(list2.groupBy(String::first))
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast()  = async(UI){
//        val result = RequestForecastCommand(zipCode).execute()
//
//        uiThread {
//                        val adapter = ForecastListAdapter(result) {
//                                startActivity<DetailActivity>(DetailActivity.ID to it.id,
//                                               DetailActivity.CITY_NAME to result.city)
//                           }
//                       forecastList.adapter = adapter
//                        toolbarTitle = "${result.city} (${result.country})"
//
//    }

        val result = bg { RequestForecastCommand(zipCode).execute() }
        updateUI(result.await())
    }

    private fun updateUI(weekForecast: ForecastList){
            val adapter = ForecastListAdapter(weekForecast){
            startActivity<DetailActivity>(DetailActivity.ID to it.id,
                    DetailActivity.CITY_NAME to weekForecast.city)
        }

        forecastList.adapter = adapter
        toolbarTitle = "${weekForecast.city}(${weekForecast.country})"

    }

    fun toast(message: String,
              tag: String = MainActivity::class.java.simpleName,
              lenght: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, "[$tag] $message", lenght).show()
    }

//    fun mix(c1: Int, c2: Int)=
//            when(setOf(c1, c2)){
//                setOf(Color.RED, Color.YELLOW) -> "Orange"
//                setOf(Color.YELLOW, Color.BLUE) -> "Green"
//                setOf(Color.BLUE, Color.BLACK) -> "Indigo"
//                else -> "Dirty color"
//            }
//
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7",
        "Sun 6/29 - Sunny - 20/7"
    )

//    fun <T> Collection<T>.joinToString(
//            separator : String = ", ",
//            prefix : String = "",
//            postfix : String = ""): String{
//        val result = StringBuilder(prefix)
//
//        for((index, element) in this.withIndex()){
//            if(index > 0) result.append(separator)
//            result.append(element)
//        }
//
//        result.append(postfix)
//        return result.toString()
//    }
}
