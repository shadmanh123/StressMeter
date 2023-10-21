package com.example.shadman_hossain_stressmeter.ui.imageRequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageRequestViewModel : ViewModel() {
    private lateinit var currentSet: Set<Int>
    private lateinit var updatedSet: MutableSet<Int>
    private val _selectedImages = MutableLiveData<Set<Int>>()
    val selectedImages: LiveData<Set<Int>> = _selectedImages

    fun addSelectedImages(images: Set<Int>){
        currentSet = _selectedImages.value?: setOf()
        updatedSet = (currentSet + images).take(16).toMutableSet()
        _selectedImages.value = updatedSet
    }
    fun removeSelectedImages(imageResourceId: Int){
        currentSet = _selectedImages.value?: setOf()
        updatedSet = currentSet.toMutableSet()
        updatedSet.remove(imageResourceId)
        _selectedImages.value = updatedSet
    }

}