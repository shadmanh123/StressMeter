package com.example.shadman_hossain_stressmeter.ui.visualization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lecho.lib.hellocharts.model.PointValue

class VisualizationViewModel : ViewModel() {
    private val _visualizationData = MutableLiveData<List<PointValue>>()
    val visualizationData: LiveData<List<PointValue>> = _visualizationData

    fun updateVisualizationData(data: List<PointValue>){
        viewModelScope.launch {
            _visualizationData.value = data
        }
    }
}