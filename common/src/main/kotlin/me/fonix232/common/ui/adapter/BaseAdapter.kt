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

abstract class BaseViewHolder<T: Any, out B: ViewDataBinding>(internal val binding: B, internal val onClick: (View, T) -> Unit): RecyclerView.ViewHolder(binding.root) {

    internal var data: T? = null

    init {
        itemView.setOnClickListener { onClick(itemView, data!!) }
    }

    open fun bind(data: T?) {
        this.data = data!!
    }
}

abstract class BaseAdapter<T: Any, B: ViewDataBinding, VH: BaseViewHolder<T, B>>(
    internal val items: LiveData<List<T>>,
    internal val owner: LifecycleOwner,
    @LayoutRes internal val layout: Int,
    internal val onClick: (View, T) -> Unit): RecyclerView.Adapter<VH>() {

    init {
        this.items.observe(owner, Observer { notifyDataSetChanged() })
    }

    override fun getItemCount(): Int = items.value?.size ?: 0

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
    // TODO: Add this to subclass with proper VH instance creation
    // override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(inflate(parent), onClick)

    internal fun getItem(position: Int): T? = items.value?.get(position)

    internal fun inflate(parent: ViewGroup): B = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false)
}