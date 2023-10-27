package com.wahidabd.synrgy.presentation.comment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.synrgy.databinding.ItemCommentBinding
import com.wahidabd.synrgy.domain.comment.model.Comment


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private val list = mutableListOf<Comment>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Comment>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    private var onDeleteListener: ((id: Long) -> Unit)? = null
    fun setOnDeleteListener(listener: (id: Long) -> Unit) {
        onDeleteListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentAdapter.CommentViewHolder =
        CommentViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: CommentAdapter.CommentViewHolder, position: Int) {
        holder.bind(list[position], onDeleteListener)
    }

    override fun getItemCount(): Int = list.size

    inner class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Comment, onDeleteListener: ((id: Long) -> Unit)? = null) = with(binding) {
            tvUsername.text = data.name
            tvComment.text = data.body

            imgDelete.setOnClickListener {
                onDeleteListener?.invoke(data.id ?: 0L)
            }
        }
    }
}