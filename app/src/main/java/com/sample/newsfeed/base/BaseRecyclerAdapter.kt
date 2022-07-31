package com.sample.newsfeed.base

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> :
    RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var items = ArrayList<T>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return createBaseViewHolder(parent = parent, viewType = viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        if (position < 0 || position >= items.size)
            return
        if (items[position] != null)
            holder.bindItem(items[position])
    }

    fun setItems(items: ArrayList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItems(): ArrayList<T> {
        val list = arrayListOf<T>()
        list.addAll(items)
        return list
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    private fun removeItem(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    fun diffUtilRefresh(
        diffResult: DiffUtil.DiffResult,
        newList: ArrayList<T>
    ) {
        items.clear()
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    protected abstract fun createBaseViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<T>
}