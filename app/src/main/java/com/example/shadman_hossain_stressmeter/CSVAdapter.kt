package com.example.shadman_hossain_stressmeter

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.File

class CSVAdapter(context: Context) {
    private val context:Context = context
    fun writeDataToCsvFile(csvData: String) {
        val filename = "your_results.csv"
        val outputStream = context.openFileOutput(filename, Context.MODE_APPEND)
        outputStream.write(csvData.toByteArray())
        outputStream.write("\n".toByteArray()) // Add a newline for the next entry
        outputStream.close()
    }
    fun readDataFromCSVFile():List<String>{
        val data = mutableListOf<String>()
        val file = File(context.filesDir, "your_results.csv")
        if (file.exists()) {
            val reader = BufferedReader(file.reader())
            reader.useLines { lines ->
                lines.forEach {
                    data.add(it)
                }
            }
        }
        return data
    }

}