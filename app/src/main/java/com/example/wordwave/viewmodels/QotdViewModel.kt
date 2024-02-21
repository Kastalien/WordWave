package com.example.wordwave.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordwave.data.Quote
import com.example.wordwave.data.RetrofitInstance
import kotlinx.coroutines.launch

class QotdViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api
    //val posts: MutableState<List<Post>> = mutableStateOf(emptyList())
    var currentQuote by mutableStateOf<Quote>(Quote())
    fun getQotd() {
        viewModelScope.launch {
            try {
                val response = apiService.getQotd()
                response.quote?.let{
                    currentQuote = it
                }
            } catch (e: Exception) {
                Log.d("quote",e.message?:"unknown")
            }
        }
    }
}