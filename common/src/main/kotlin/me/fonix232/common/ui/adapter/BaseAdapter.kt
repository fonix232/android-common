package me.fonix232.common.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import me.fonix232.common.BR

abstract class BaseViewHolder<T : Any, out B : ViewDataBinding>(
    protected val binding: B,
    protected val onClick: (View, T) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    var data: T? = null

    init {
        itemView.setOnClickListener { onClick(itemView, data!!) }
    }

    open fun bind(data: T?) {
        this.data = data!!
        binding.setVariable(BR.data, data)
        binding.executePendingBindings()
    }
}

abstract class BaseAdapter<T : Any, B : ViewDataBinding, VH : BaseViewHolder<T, B>>(
    protected val items: LiveData<List<T>>,
    protected val owner: LifecycleOwner,
    @LayoutRes protected val layout: Int,
    protected val onClick: (View, T) -> Unit
) : RecyclerView.Adapter<VH>() {

    init {
        this.items.observe(owner, Observer { notifyDataSetChanged() })
    }

    override fun getItemCount(): Int = items.value?.size ?: 0

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
    // TODO: Add this to subclass with proper VH instance creation
    // override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(inflate(parent), onClick)

    protected open fun getItem(position: Int): T? = items.value?.get(position)

    protected open fun inflate(parent: ViewGroup): B =
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false)
}