package me.fonix232.common.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import me.fonix232.common.viewmodel.BaseViewModel
import me.fonix232.common.bind
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import org.koin.standalone.KoinComponent
import kotlin.reflect.KClass

abstract class AutoActivity<B: ViewDataBinding>(@LayoutRes layout: Int): AppCompatActivity() {
    val binding: B by bind(layout)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding.setLifecycleOwner(this)
    }
}

abstract class BaseActivity<VM: BaseViewModel, B: ViewDataBinding>(vmClass: KClass<VM>, @LayoutRes layout: Int): AutoActivity<B>(layout), KoinComponent {
    val viewModel: VM by viewModelByClass(vmClass)
}