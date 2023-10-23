package com.example.shadman_hossain_stressmeter.ui.imageRequest

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class ImageAdapter(private val context: Context, private var grid: List<Int>,
    private val gridScores: List<Int>): BaseAdapter() {
    override fun getCount(): Int {
        return grid.size
    }

    override fun getItem(position: Int): Any {
        return grid[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView:ImageView
        if(convertView == null){
            imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(250,250)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        else{
            imageView = convertView as ImageView
        }

        imageView.setImageResource(grid[position])
        return imageView
    }

    fun updateImages(newGrid: List<Int>){
        grid = newGrid
        notifyDataSetChanged()
    }

    fun getScore(position: Int): Int{
        return gridScores[position]
    }

    fun getDrawableName(grid: List<Int>, position: Int): String {
        val imageResourceID = grid[position]
        return context.resources.getResourceName(imageResourceID)
    }
}