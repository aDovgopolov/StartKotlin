package com.example.w.startkotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.w.startkotlin.R
import com.example.w.startkotlin.domain.model.Forecast
import com.example.w.startkotlin.domain.model.ForecastList
import com.example.w.startkotlin.extensions.ctx
import com.example.w.startkotlin.extensions.toDateString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*


//class ForecastListAdapter(val items: List<String>) :
//         RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
//
//     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
//             ViewHolder {
//         return ViewHolder(TextView(parent.context))
//         }
//
//     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//         holder.textView.text = items[position]
//         }
//
//     override fun getItemCount(): Int = items.size
//
//     class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
//     }

class ForecastListAdapter(val weekForecast: ForecastList,
                          val itemClick: (Forecast) -> Unit):
                            RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view  = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size


    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit):
            RecyclerView.ViewHolder(view){

        fun bindForecast(forecast: Forecast){
            with(forecast){
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${high}º"
                itemView.minTemperature.text = "${low}º"
                itemView.setOnClickListener{itemClick(this)}
            }
        }
    }
}