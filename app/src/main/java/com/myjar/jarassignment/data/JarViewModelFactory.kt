package com.myjar.jarassignment.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.myjar.jarassignment.data.repository.JarRepository
import com.myjar.jarassignment.ui.vm.JarViewModel

class JarViewModelFactory(
    private val repository: JarRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom((JarViewModel::class.java))){
            return JarViewModel(repository) as T
        }
        throw  IllegalArgumentException("unknown viewmodel class")
    }
}