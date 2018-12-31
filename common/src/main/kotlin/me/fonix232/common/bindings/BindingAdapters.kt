package me.fonix232.common.bindings

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("android:src")
fun setImageRawData(imageView: ImageView, data: ByteArray?) {
    if(data == null || data.isEmpty()) return

    val bm = BitmapFactory.decodeByteArray(data, 0, data.size)
    imageView.setImageBitmap(bm)
}

@InverseBindingAdapter(attribute = "refreshing", event = "refreshingAttrChanged")
fun isRefreshing(view: SwipeRefreshLayout): Boolean = view.isRefreshing

@BindingAdapter("refreshing")
fun setRefreshing(view: SwipeRefreshLayout, refreshing: Boolean) {
    if (refreshing != view.isRefreshing) {
        view.isRefreshing = refreshing
    }
}

@BindingAdapter(value = ["onRefreshListener", "refreshingAttrChanged"], requireAll = false)
fun setOnRefreshListener(view: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener?, refreshingAttrChanged: InverseBindingListener?) {

    val newValue = SwipeRefreshLayout.OnRefreshListener {
        if (listener != null) {
            refreshingAttrChanged?.onChange()
            listener.onRefresh()
        }
    }

    view.setOnRefreshListener(null)
    view.setOnRefreshListener(newValue)
}