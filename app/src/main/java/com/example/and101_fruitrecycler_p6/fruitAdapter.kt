package com.example.and101_fruitrecycler_p6

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FruitAdapter( private val fruitList: MutableList<Fruit>) : RecyclerView.Adapter<FruitAdapter.FruitViewHolder>(){

    class FruitViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val fruitText1: TextView
        val fruitText2: TextView
        val fruitText3: TextView

        init{
            fruitText1 = view.findViewById(R.id.textView1)
            fruitText2 = view.findViewById(R.id.textView2)
            fruitText3 = view.findViewById(R.id.textView3)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_items, parent, false)
        return FruitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val currentFruit = fruitList[position]
        // Set the text of TextViews based on the data model (Fruit)
        holder.fruitText1.text = "Name: ${currentFruit.name}"
        holder.fruitText2.text = "Family: ${currentFruit.family}"
        holder.fruitText3.text = "Order: ${currentFruit.order}"
        Log.d("FruitAdapter", "Name: ${currentFruit.name}")

    }

}