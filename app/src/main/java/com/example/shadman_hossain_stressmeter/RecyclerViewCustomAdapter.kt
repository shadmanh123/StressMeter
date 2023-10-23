package com.example.shadman_hossain_stressmeter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class StressData(val score: Float, val timestamp: String)
class RecyclerViewCustomAdapter(private val dataSet: List<StressData>):
    RecyclerView.Adapter<RecyclerViewCustomAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewScore: TextView = view.findViewById(R.id.textViewScore)
        val textViewTimestamp: TextView = view.findViewById(R.id.textViewTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_stress_data, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = dataSet[position]
            holder.textViewScore.text = item.score.toString()
            holder.textViewTimestamp.text = item.timestamp.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
