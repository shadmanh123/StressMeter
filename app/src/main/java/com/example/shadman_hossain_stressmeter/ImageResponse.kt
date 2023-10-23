package com.example.shadman_hossain_stressmeter

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageResponse: AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var cancelButton: Button
    private lateinit var submitButton: Button
    private val csvAdapter = CSVAdapter(this)
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
        cancelButton.setOnClickListener {
            finish()
        }
        submitButton.setOnClickListener {
            val timeStamp = System.currentTimeMillis()
            val csvData = "$score, $timeStamp"
            val coroutine = CoroutineScope(Dispatchers.IO).launch {
                csvAdapter.writeDataToCsvFile(csvData)
            }
            CoroutineScope(Dispatchers.Main).launch {
                coroutine.join()
                finishAffinity()
            }

        }

    }
}