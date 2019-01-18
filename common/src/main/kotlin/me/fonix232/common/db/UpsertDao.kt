package me.fonix232.common.db

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface UpsertDao<T> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(entities: List<T>)

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(entity: T)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(entity: T)

    @Delete()
    fun delete(entity: T)

    fun upsert(entity: T) {
        try {
            insert(entity)
        } catch (e: SQLiteConstraintException) {
            update(entity)
        }
    }

    fun upsertAll(entities: Iterable<T>?) = entities?.forEach { upsert(it) }
    fun deleteAll()
}