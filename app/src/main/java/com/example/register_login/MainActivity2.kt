package com.example.register_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.register_login.databinding.ActivityMain2Binding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {
     lateinit var stateAdapter:StateAdapter
     private lateinit var bind:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.itemheader,bind.list,false))
        fetchResult()
    }
    private fun fetchResult(){
        GlobalScope.launch (Dispatchers.Main){
            val response = withContext(Dispatchers.IO){Client.api.execute()}
            if(response.isSuccessful)
            {
                //    response.body?.string()?.let { Log.i("info", it) }
                val data = Gson().fromJson(response.body?.string(),Response::class.java)
                launch(Dispatchers.Main) {
                    bindCombinedData(data.statewise[0])
                    bindStatewisedata(data.statewise.subList(0,data.statewise.size))
                }
            }
        }

    }
    private fun bindStatewisedata(subList: List<StatewiseItem>) {
        stateAdapter=StateAdapter((subList))
        bind.list.adapter=stateAdapter
    }
    private fun bindCombinedData(data: StatewiseItem) {
        val lastUpdatedTime = data.lastupdatedtime
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        bind.lastUpdatedTV.text= "Last updated\n ${getTimeAgo(simpleDateFormat.parse(lastUpdatedTime))}"
        bind.confirmedTV.text=data.confirmed
        bind.recoveredTV.text=data.recovered
        bind.activeTV.text=data.active
        bind.deadTV.text=data.deaths
    }
    fun getTimeAgo(past: Date):String{
        val now = Date()
        val seconds = TimeUnit.MICROSECONDS.toSeconds(now.time-past.time)
        val minutes = TimeUnit.MICROSECONDS.toMinutes(now.time-past.time)
        val hours   = TimeUnit.MICROSECONDS.toHours(now.time-past.time)
        return when{
            seconds<60 ->{
                "Few Seconds ago"
            }
            minutes<60 ->{
                "$minutes minutes ago"
            }
            hours<24 -> {
                "$hours hour ${minutes % 60 } minute ago"
            }
            else -> {
                SimpleDateFormat("dd/MM/yy, hh:mm a").format(past.toString())
            }
        }
    }

}