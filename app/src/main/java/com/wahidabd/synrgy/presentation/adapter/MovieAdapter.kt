package com.wahidabd.synrgy.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.synrgy.databinding.ItemGenreGridBinding
import com.wahidabd.synrgy.domain.Movie
import com.wahidabd.synrgy.utils.loadImageUrl


/**
 * Created by Wahid on 10/11/2023.
 * Github github.com/wahidabd.
 */


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val list = mutableListOf<Movie>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Movie>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    private var listener: ((Movie) -> Unit)? = null
    fun setOnClickListener(listener: (Movie) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            ItemGenreGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    inner class MovieViewHolder(private val binding: ItemGenreGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie, listener: ((Movie) -> Unit)? = null) = with(binding) {
            tvName.text = data.title
            image.loadImageUrl("https://image.tmdb.org/t/p/original${data.poster_path}")

            card.setOnClickListener { listener?.invoke(data) }
        }
    }
}