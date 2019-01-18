package me.fonix232.common.db

interface BaseDao<T>: UpsertDao<T> {
    fun getAll(): List<T>
    fun get(id: Int): T
}