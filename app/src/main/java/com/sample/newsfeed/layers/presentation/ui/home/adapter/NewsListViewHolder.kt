package com.sample.newsfeed.layers.presentation.ui.home.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sample.core.extensions.empty
import com.sample.core.extensions.safeGet
import com.sample.news.R
import com.sample.newsfeed.base.BaseViewHolder
import com.sample.news.databinding.ItemEachNewsBinding
import com.sample.newsfeed.layers.domain.model.ResultsItem
import com.sample.newsfeed.layers.presentation.ui.event.ClickEvent

/**
 * View holder class bind the data to the view
 */
class NewsListViewHolder(
    private val recyclerBinding: ItemEachNewsBinding,
    event: (ClickEvent) -> Unit
) : BaseViewHolder<ResultsItem>(event, recyclerBinding.root) {

    // region OVERRIDDEN

    override fun bindItem(item: ResultsItem) {
        loadNewsImage(item)
        showHeadingSnippetAndDate(item)
    }

    // endregion


    // region UTIL

    private fun loadNewsImage(item: ResultsItem) {
        item.media?.let { media ->
            if (media.isNotEmpty()) {

                var i = 2
                var url = String.empty
                while (i > -1) {
                    url = media[0]?.mediaMetadata?.get(i)?.url.safeGet()
                    if (url.isNotEmpty())
                        break
                    i--
                }

                Glide.with(recyclerBinding.newsImages.context)
                    .load(url)
                    .centerCrop()
                    .error(R.drawable.background_gradient)
                    .placeholder(R.drawable.background_gradient)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(recyclerBinding.newsImages)
            } else {
                setDefaultImage()
            }
        } ?: setDefaultImage()

    }

    private fun setDefaultImage() {
        recyclerBinding.newsImages.setImageResource(R.drawable.background_gradient)
    }

    private fun showHeadingSnippetAndDate(item: ResultsItem) {
        recyclerBinding.apply {
            headingTextView.text = item.title?.safeGet()
            newsSnippetTextView.text = item.jsonMemberAbstract
            dateTextView.text = item.publishedDate?.substring(0, 10)
        }
    }

    // endregion

}
