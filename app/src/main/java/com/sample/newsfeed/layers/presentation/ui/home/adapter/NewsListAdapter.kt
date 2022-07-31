package com.sample.newsfeed.layers.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sample.news.databinding.ItemEachNewsBinding
import com.sample.newsfeed.base.BaseRecyclerAdapter
import com.sample.newsfeed.base.BaseViewHolder
import com.sample.newsfeed.layers.domain.model.ResultsItem
import com.sample.newsfeed.layers.presentation.ui.event.ClickEvent

/**
 * Adapter class which create the view for the item
 */
class NewsListAdapter(
    private val event: (ClickEvent) -> Unit
) : BaseRecyclerAdapter<ResultsItem>() {

    override fun createBaseViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ResultsItem> {
        return NewsListViewHolder(
            ItemEachNewsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), event
        )
    }

}
