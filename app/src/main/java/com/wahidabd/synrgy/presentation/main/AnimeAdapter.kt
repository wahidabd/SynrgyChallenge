package com.wahidabd.synrgy.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.synrgy.databinding.ItemAnimeBinding
import com.wahidabd.synrgy.domain.anime.model.Anime
import com.wahidabd.synrgy.utils.loadImageUrl


/**
 * Created by wahid on 11/6/2023.
 * Github github.com/wahidabd.
 */


class AnimeAdapter : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    private val list = mutableListOf<Anime>()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Anime>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    private var listener: ((Anime) -> Unit)? = null
    fun setOnClickListener(listener: (Anime) -> Unit){
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimeAdapter.AnimeViewHolder =
        AnimeViewHolder(ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: AnimeAdapter.AnimeViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount(): Int = list.size

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Anime, listener: ((Anime) -> Unit)? = null) = with(binding) {
            tvTitle.text = data.title

            image.loadImageUrl(data.images?.jpg?.imageUrl.toString())
        }
    }
}