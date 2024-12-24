package com.myjar.jarassignment.ui.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myjar.jarassignment.createRetrofit
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.data.repository.JarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JarViewModel(
    private val repository: JarRepository
) : ViewModel() {

    private val _listStringData = MutableStateFlow<List<ComputerItem>>(emptyList())
    val listStringData: StateFlow<List<ComputerItem>>
        get() = _listStringData

    private val _navigateToItem = MutableStateFlow<String?>(null)
    val navigateToItem: StateFlow<String?>
        get() = _navigateToItem

    fun fetchData() {
        viewModelScope.launch {
            repository.fetchResults().collect {
                _listStringData.value = it
            }
        }
//        Log.d("yzx", "List updated: ${repository.fetchResults()}")
    }

    fun navigateToItemDetail(id: String) {
//        viewModelScope.launch {
//            _navigateToItem.emit(id)
//        }
        _navigateToItem.value = id
    }
}