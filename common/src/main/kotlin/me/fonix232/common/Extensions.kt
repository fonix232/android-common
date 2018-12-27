package me.fonix232.common

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

fun <T: ViewDataBinding> Activity.bind(@LayoutRes layout: Int) = lazy {
    (DataBindingUtil.setContentView(this, layout) as T).also { if(this is LifecycleOwner) { it.setLifecycleOwner(this) } }
}

fun <T: ViewDataBinding> Fragment.bind(@LayoutRes layout: Int, inflater: LayoutInflater, container: ViewGroup?) =
        DataBindingUtil.inflate<T>(inflater, layout, container, false)