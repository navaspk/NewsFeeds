package com.sample.newsfeed.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sample.newsfeed.layers.presentation.ui.event.ClickEvent
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<T>(
    private val event: (ClickEvent) -> Unit,
    private val bindingView: View
) : RecyclerView.ViewHolder(bindingView),
    View.OnClickListener, LayoutContainer, View.OnLongClickListener {

    override val containerView = itemView

    init {
        itemView.setOnClickListener(this)
    }

    abstract fun bindItem(item: T)

    override fun onClick(p0: View?) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            event.invoke(ClickEvent.ItemClicked(adapterPosition))
        }
    }

    override fun onLongClick(v: View?): Boolean {
        return false
    }

    // endregion

}
