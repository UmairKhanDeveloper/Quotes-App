package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.db.Favorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _allQuotes = MutableStateFlow<ResultState<List<QuotesItem>>>(ResultState.Laoding)
    val allQuotes: StateFlow<ResultState<List<QuotesItem>>> = _allQuotes.asStateFlow()


    val allNotes: LiveData<List<Favorite>> = repository.getFavData()


    fun Insert(favorite: Favorite) {
        viewModelScope.launch {
            try {
                repository.Insert(favorite)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun Delete(favorite: Favorite) {
        viewModelScope.launch {
            try {
                repository.Delete(favorite)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getQuotes() {
        viewModelScope.launch {
            _allQuotes.value = ResultState.Laoding
            try {
                val response = repository.getAllQuotes()
                _allQuotes.value = ResultState.Success(response)
            } catch (e: Exception) {
                _allQuotes.value = ResultState.Error(e)
            }
        }
    }
}
