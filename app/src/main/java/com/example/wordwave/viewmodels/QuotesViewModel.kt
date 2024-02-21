package com.example.wordwave.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordwave.data.Credentials
import com.example.wordwave.data.FavResponse
import com.example.wordwave.data.Quote
import com.example.wordwave.data.RetrofitInstance
import com.example.wordwave.data.favQuotes
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Integer.min
import java.util.Collections.addAll
import kotlin.text.Typography.quote

class QuotesViewModel : ViewModel() {
    val pageSize = 5
    private val apiService = RetrofitInstance.api
    var currentPage by mutableStateOf<Int>(0)
    var pageCount: Int = 0
    var quotes = emptyList<Quote>().toMutableStateList()

    fun getQuotes(isFav:Boolean) {
        viewModelScope.launch {
            try {
                var allQuotes:List<Quote> = emptyList<Quote>()
                if (isFav){
                    allQuotes=favQuotes.getAllQuotas()
                }
                else
                {
                    val response = apiService.getQuotes()
                    allQuotes=response.quotes
                }
                if (allQuotes.isNotEmpty()) {
                    quotes.addAll(allQuotes)
                    pageCount =
                        quotes.count() / pageSize + (if (quotes.count() % pageSize == 0) 0 else 1)
                }
            } catch (e: Exception) {
                Log.d("quote", e.message ?: "unknown")
            }
        }
    }

    fun getCurrentPage():  MutableList<Quote> {
        if (quotes.isEmpty())
            return emptyList<Quote>().toMutableStateList()
        else if (!(currentPage in 0..pageCount))
            return emptyList<Quote>().toMutableStateList()
        else
            return quotes.subList(
                currentPage * pageSize,
                min((currentPage + 1) * pageSize, quotes.count())
            )
    }

    fun getStartNumeration(): Int {
        return currentPage * pageSize + 1
    }

    fun makeFav(quotaId: Int) {
        apiService.makeFav(Credentials.session, quotaId).enqueue(
            object : Callback<FavResponse> {
                override fun onResponse(call: Call<FavResponse>, response: Response<FavResponse>) {
                    Log.d("quote", "quote ${response.body()?.body ?: "NO QUOTA"}")
                    Log.d("quote", "session ${response.body()?.userDetails?.favorite.toString()}")
                    val changingIndex=quotes.indexOfFirst{ it.id == quotaId }
                    quotes[changingIndex]=quotes[changingIndex].copy(isFav = true)
                }
                override fun onFailure(call: Call<FavResponse>, t: Throwable) {
                    Log.d("quote", "failure make fav ${t.message}")
                }
            })
    }

    fun like(quotaId: Int) {
        apiService.like(Credentials.session, quotaId).enqueue(
            object : Callback<FavResponse> {
                override fun onResponse(call: Call<FavResponse>, response: Response<FavResponse>) {
                    Log.d("quote", "quote liked ${response.body()?.body ?: "NO QUOTA"}")
                    Log.d("quote", "is liked ${response.body()?.userDetails?.upVote.toString()}")
                    val changingIndex=quotes.indexOfFirst{ it.id == quotaId }
                    quotes[changingIndex]=quotes[changingIndex].copy(isLiked = true)
                    favQuotes.likeCount++
                }
                override fun onFailure(call: Call<FavResponse>, t: Throwable) {
                    Log.d("quote", "failure like ${t.message}")
                }
            })
    }
}
