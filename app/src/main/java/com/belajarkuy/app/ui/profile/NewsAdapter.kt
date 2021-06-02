package com.belajarkuy.app.ui.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.belajarkuy.app.data.model.ArticlesItem
import com.belajarkuy.app.databinding.ItemNewsBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(
    private val news: MutableList<ArticlesItem>,
    private val listener: Listener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position], listener)
    }

    override fun getItemCount(): Int = news.size

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticlesItem, listener: Listener) {
            with(binding) {
                tvNewsTitle.text = data.title
                tvDescription.text = data.description
                tvPublishAt.text = data.publishedAt.convertTime()
                Glide.with(itemView.context)
                    .load(data.urlToImage)
                    .into(ivThumbnail)
            }
            itemView.setOnClickListener {
                listener.onClick(data)
            }
        }

        @SuppressLint("SimpleDateFormat")
        private fun String.convertTime(): String {
            val time = Calendar.getInstance().time
            val sdf = SimpleDateFormat("dd MMM yyyy")
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            return sdf.format(time)
        }
    }

    interface Listener {
        fun onClick(news: ArticlesItem)
    }
}