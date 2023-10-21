package com.example.shadman_hossain_stressmeter.ui.imageRequest

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class ImageAdapter(private val context: Context, private val imageResourceIds: Array<Int>): BaseAdapter() {
    override fun getCount(): Int {
        return imageResourceIds.size
    }

    override fun getItem(position: Int): Any {
        return imageResourceIds[position]
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
        imageView.setImageResource(imageResourceIds[position])
        return imageView
    }
}