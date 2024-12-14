package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Delete
import com.example.myapplication.db.Favorite
import com.example.myapplication.db.FavoriteDataBase

class Repository(private val dataBase: FavoriteDataBase) : ApiClient {

    override suspend fun getAllQuotes(): List<QuotesItem> {
        return QoutesApiClient.getAllQuotes()
    }


    fun getFavData(): LiveData<List<Favorite>> {
        return dataBase.getDao().getAllNotes()
    }

    suspend fun Insert(favorite: Favorite) {
        dataBase.getDao().Insert(favorite)
    }
    suspend fun Delete(favorite: Favorite){
        dataBase.getDao().Delete(favorite)
    }
}
