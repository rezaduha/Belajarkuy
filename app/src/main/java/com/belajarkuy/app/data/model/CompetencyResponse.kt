package com.belajarkuy.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProgressResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("results")
	val results: List<CompetencyItem>,

	@field:SerializedName("status")
	val status: Boolean
) : Parcelable

@Parcelize
data class CompetencyItem(

	@field:SerializedName("material")
	val material: List<String>,

	@field:SerializedName("subject")
	val subject: String,

	@field:SerializedName("progress")
	val progress: Int
) : Parcelable
