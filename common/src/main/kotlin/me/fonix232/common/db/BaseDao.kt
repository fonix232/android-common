package me.fonix232.common.db

interface BaseDao<T>: UpsertDao<T> {
    fun getAll(): Iterable<T>
    fun get(id: Int): T
}