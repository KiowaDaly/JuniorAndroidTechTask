package com.kiowa.juniorandroidtechtask


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.kiowa.juniorandroidtechtask.adapter.CountryAdapter
import com.kiowa.juniorandroidtechtask.models.Country
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCountryData()
    }
    private fun getCountryData(){
        val key = resources.getString(R.string.rapid_api_key)
        val url = "https://restcountries-v1.p.rapidapi.com/all"
        Log.i("Country:","Attempting to retrieve country data")

        val client = OkHttpClient()
        val request = Request.Builder()
            .addHeader("x-rapidapi-key",key)
            .addHeader("x-rapidapi-host","restcountries-v1.p.rapidapi.com")
            .url(url)
            .build()
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call?, e: IOException) {
                Log.i("Country","   Failed to resolve countries")
            }

            override fun onResponse(call: Call?, response: Response) {
                val body = response.body()?.string()
                //Log.i("Country",body)
                val gson = GsonBuilder().create()
                val apiResponse:Array<Country>  = gson.fromJson(body, Array<Country>::class.java)
                Log.i("Country:", apiResponse[0].name)

                runOnUiThread {

                    CountryRecycler.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = CountryAdapter(apiResponse,supportFragmentManager)
                } }

            }

        })

    }

}
