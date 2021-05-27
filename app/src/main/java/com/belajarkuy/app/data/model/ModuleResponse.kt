package com.belajarkuy.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModuleResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("modules")
	val modules: List<ModulesItem>,

	@field:SerializedName("status")
	val status: Boolean
) : Parcelable

@Parcelize
data class ModulesItem(

	@field:SerializedName("duration")
	val duration: Int,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("total_questions")
	val totalQuestions: Int,

	@field:SerializedName("subject")
	val subject: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable
