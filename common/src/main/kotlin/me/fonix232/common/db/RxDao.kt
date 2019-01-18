package me.fonix232.common.db

import io.reactivex.Flowable

interface RxDao<T>: UpsertDao<T> {
    fun getAll(): Flowable<List<T>>
    fun get(id: Int): Flowable<T>
}