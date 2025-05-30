package com.example.empyreancodeassessment.features.itemDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.empyreancodeassessment.R
import com.example.empyreancodeassessment.network.models.CommentResponse

class ItemDetailCommentAdapter(
    private val itemComments: List<CommentResponse>
) : RecyclerView.Adapter<ItemDetailCommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDetailCommentViewHolder {
        return ItemDetailCommentViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_detail_comment_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemDetailCommentViewHolder, position: Int) {
        val itemComment = itemComments[position]

        with(holder.itemView) {
            findViewById<TextView>(R.id.item_detail_comment_item_author_text).text =
                itemComment.author
            findViewById<TextView>(R.id.item_detail_comment_item_timestamp_text).text =
                itemComment.timestamp
            findViewById<TextView>(R.id.item_detail_comment_item_message_text).text =
                itemComment.message
        }
    }

    override fun getItemCount(): Int = itemComments.size
}

class ItemDetailCommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)