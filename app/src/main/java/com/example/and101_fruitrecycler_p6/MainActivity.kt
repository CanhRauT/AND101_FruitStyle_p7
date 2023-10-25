package com.example.and101_fruitrecycler_p6

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var fruitList: MutableList<Fruit>
    private lateinit var recyclerViewFruit: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewFruit = findViewById(R.id.fruitRecycler)
        fruitList = mutableListOf() // List of Fruit objects

        // Set the layout manager
        recyclerViewFruit.layoutManager = LinearLayoutManager(this)

        // Automatically fetch fruit information when the activity starts
        getFruitInfo()

        // Create a FruitAdapter and set it to the RecyclerView
        val fruitAdapter = FruitAdapter(fruitList)
        recyclerViewFruit.adapter = fruitAdapter
    }

    private fun getFruitInfo() {
        val client = AsyncHttpClient()

        client.get("https://www.fruityvice.com/api/fruit/all", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Fruit API Success", json.jsonArray.toString())
                val jsonArray = json.jsonArray

                for (i in 0 until jsonArray.length()) {
                    val fruitData = jsonArray.getJSONObject(i)

                    val fruit = Fruit(
                        name = fruitData.getString("name"),
                        family = fruitData.getString("family"),
                        order = fruitData.getString("order")
                    )

                    fruitList.add(fruit)
                }

                val fruitAdapter = FruitAdapter(fruitList)
                recyclerViewFruit.adapter = fruitAdapter
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Fruit API Error", errorResponse)
            }
        })

    }
}

data class Fruit(
    val name: String,
    val family: String,
    val order: String
)
