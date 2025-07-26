package com.shubham.brainyexplorers.ui.labs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.brainyexplorers.data.model.LabItem
import com.shubham.brainyexplorers.data.repository.LabsRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for Labs list. Fetches data from repository and exposes it as LiveData.
 */
class LabsViewModel(private val repository: LabsRepository = LabsRepository()) : ViewModel() {
    private val _labs = MutableLiveData<List<LabItem>>()
    val labs: LiveData<List<LabItem>> = _labs

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    /**
     * Loads labs from Firestore and posts to LiveData.
     */
    fun loadLabs() {
        viewModelScope.launch {
            val result = repository.getLabs()
            if (result.isSuccess) {
                _labs.value = result.getOrNull()!!
                _error.value = null
            } else {
                _error.value = result.exceptionOrNull()?.localizedMessage
            }
        }
    }
} 