package com.example.shadman_hossain_stressmeter.ui.imageRequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageRequestViewModel : ViewModel() {
    private val _selectedImages = MutableLiveData<Set<Int>>()

    fun addSelectedImages(images: Set<Int>){
        _selectedImages.value = images
    }

}