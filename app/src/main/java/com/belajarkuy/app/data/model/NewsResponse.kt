package com.belajarkuy.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>,

	@field:SerializedName("status")
	val status: String
) : Parcelable

@Parcelize
data class ArticlesItem(

	@field:SerializedName("publishedAt")
	val publishedAt: String,

	@field:SerializedName("urlToImage")
	val urlToImage: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String
) : Parcelable
