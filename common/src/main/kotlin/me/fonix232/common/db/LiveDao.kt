package me.fonix232.common.db

import androidx.lifecycle.LiveData

interface LiveDao<T>: UpsertDao<T> {
    fun getAll(): LiveData<List<T>>
    fun get(id: Int): LiveData<T>
}