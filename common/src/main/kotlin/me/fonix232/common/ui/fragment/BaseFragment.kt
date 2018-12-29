package me.fonix232.common.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import me.fonix232.common.BR
import me.fonix232.common.bind
import me.fonix232.common.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import org.koin.standalone.KoinComponent
import kotlin.reflect.KClass

abstract class AutoFragment<B : ViewDataBinding>(@LayoutRes private val layout: Int) : Fragment() {
    private lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        bind<B>(layout, inflater, container, ::afterBind).root

    open fun afterBind(binding: B) {
        this.binding = binding
        binding.setLifecycleOwner(this)
    }
}

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding>(vmClass: KClass<VM>, @LayoutRes layout: Int) :
    AutoFragment<B>(layout), KoinComponent {
    private val viewModel: VM by viewModelByClass(vmClass)

    override fun afterBind(binding: B) {
        super.afterBind(binding)
        binding.setVariable(BR.viewModel, viewModel)
    }
}