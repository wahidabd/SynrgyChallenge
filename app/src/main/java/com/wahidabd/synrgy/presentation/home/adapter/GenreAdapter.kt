package com.wahidabd.synrgy.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wahidabd.synrgy.databinding.ItemGenreGridBinding
import com.wahidabd.synrgy.databinding.ItemGenreRectangleBinding
import com.wahidabd.synrgy.domain.movie.Genre
import com.wahidabd.synrgy.utils.enums.GenreType
import com.wahidabd.synrgy.utils.loadImageUrl


/**
 * Created by Wahid on 10/11/2023.
 * Github github.com/wahidabd.
 */


class GenreAdapter() : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private var type: GenreType = GenreType.LIST
    private val list = mutableListOf<Genre>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Genre>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    private var listener: ((Genre) -> Unit)? = null
    fun setOnClickListener(listener: (Genre) -> Unit) {
        this.listener = listener
    }

    fun setOnGenreType(type: GenreType) {
        this.type = type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        when(type) {
             GenreType.LIST -> {
                ListViewHolder(
                    ItemGenreRectangleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            GenreType.GRID -> {
                GridViewHolder(
                    ItemGenreGridBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val item = list[position]

        when(holder){
            is ListViewHolder -> holder.bind(item, listener)
            is GridViewHolder -> holder.bind(item, listener)
        }
    }

    abstract class GenreViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    inner class ListViewHolder(private val binding: ItemGenreRectangleBinding) :
        GenreViewHolder(binding) {
        fun bind(data: Genre, listener: ((Genre) -> Unit)? = null) = with(binding) {
            tvGenre.text = data.name
            tvGenre.setOnClickListener { listener?.invoke(data) }
        }
    }

    inner class GridViewHolder(private val binding: ItemGenreGridBinding) :
        GenreViewHolder(binding) {
        fun bind(data: Genre, listener: ((Genre) -> Unit)? = null) = with(binding) {
            tvName.text = data.name
            image.loadImageUrl(data.image)

            card.setOnClickListener { listener?.invoke(data) }
        }
    }
}