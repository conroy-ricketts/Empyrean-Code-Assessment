package com.example.empyreancodeassessment.features.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.empyreancodeassessment.AppEngine
import com.example.empyreancodeassessment.R
import com.example.empyreancodeassessment.network.models.ItemResponse

class FeedAdapter(
    private val feedItems: List<ItemResponse>
) : RecyclerView.Adapter<FeedItemViewHolder>() {
    private lateinit var feedItemNavigator: FeedItemNavigator

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
        feedItemNavigator = parent.context as FeedItemNavigator

        return FeedItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.feed_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
        val feedItem = feedItems[position]

        with(holder.itemView) {
            findViewById<TextView>(R.id.feed_item_title_text).text = feedItem.title
            findViewById<TextView>(R.id.feed_item_summary_text).text = feedItem.summary
            setOnClickListener {
                AppEngine.getInstance().currentItem = feedItem
                feedItemNavigator.navigateToItemDetail()
            }
        }
    }

    override fun getItemCount(): Int = feedItems.size
}

class FeedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)