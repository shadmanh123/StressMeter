package com.example.shadman_hossain_stressmeter

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shadman_hossain_stressmeter.ui.imageRequest.ImageAdapter

class imageResponse: AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var cancelButton: Button
    private lateinit var submitButton: Button
    val csvAdapter = CSVAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_response)
        imageView = findViewById(R.id.selectedImage)
        cancelButton = findViewById(R.id.cancel_button)
        submitButton = findViewById(R.id.submit_button)
        val drawableName: String? = intent.getStringExtra("drawableName")
        val score:Int = intent.getIntExtra("score", 0)
        if (drawableName != null){
            val imageResouceID = resources.getIdentifier(drawableName, "drawable", packageName)
            if(imageResouceID != 0){
                imageView.setImageResource(imageResouceID)
            }
        }
        cancelButton.setOnClickListener(){
            finish()
        }
        submitButton.setOnClickListener(){
            var timeStamp = System.currentTimeMillis()
            var csvData = "$score, $timeStamp"
            csvAdapter.writeDataToCsvFile(csvData)
        }

    }
}