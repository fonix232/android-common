package me.fonix232.common

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import me.fonix232.common.db.UpsertDao

fun <T> UpsertDao<T>.upsertAll(entities: Iterable<T>) = entities.forEach { upsert(it) }

fun <T : ViewDataBinding> Activity.bind(@LayoutRes layout: Int, afterBind: (T) -> Unit = {}) = lazy {
    (DataBindingUtil.setContentView(this, layout) as T).also { afterBind(it) }
}

fun <T : ViewDataBinding> Fragment.bind(
    @LayoutRes layout: Int, inflater: LayoutInflater,
    container: ViewGroup?,
    afterBind: (T) -> Unit
) =
    DataBindingUtil.inflate<T>(inflater, layout, container, false).also { afterBind(it) }