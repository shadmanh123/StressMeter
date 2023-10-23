package com.example.shadman_hossain_stressmeter.ui.imageRequest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageRequestViewModel : ViewModel() {
//    private lateinit var currentSet: Set<Int>
//    private lateinit var updatedSet: MutableSet<Int>
    private val _selectedImages = MutableLiveData<Set<Int>>()
    private val selectedImagesWithScores = mutableSetOf<Pair<Int, Int>>()

    fun addSelectedImages(images: Set<Int>){
//        currentSet = _selectedImages.value?: setOf()
//        updatedSet = currentSet.toMutableSet()
        _selectedImages.value = images
    }
    fun imageClicked(imageResourceId: Int, score: Int){
        val pair = Pair(imageResourceId, score)
        if (selectedImagesWithScores.contains(pair)) {
            selectedImagesWithScores.remove(pair)
        } else {
            selectedImagesWithScores.add(pair)
        }
    }
//    fun removeSelectedImages(imageResourceId: Int){
//        currentSet = _selectedImages.value?: setOf()
//        updatedSet = currentSet.toMutableSet()
//        updatedSet.remove(imageResourceId)
//        _selectedImages.value = updatedSet
//    }

}