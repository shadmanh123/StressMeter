package com.example.shadman_hossain_stressmeter.ui.imageRequest

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class ImageAdapter(private val context: Context, private var grid: List<Int>): BaseAdapter() {
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
//            Log.d("ImageAdapter", "Creating a new ImageView for position $position")
            imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(250,250)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        else{
//            Log.d("ImageAdapter", "Reusing an existing ImageView for position $position")
            imageView = convertView as ImageView
        }

        imageView.setImageResource(grid[position])
        return imageView
    }

    fun updateImages(newGrid: List<Int>){
        grid = newGrid
        notifyDataSetChanged()
    }
}