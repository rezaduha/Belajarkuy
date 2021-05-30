package com.belajarkuy.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthRequest(

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("Id")
	val id: String?,

	@field:SerializedName("photo_url")
	val photoUrl: String?,

	@field:SerializedName("email")
	val email: String?,

	@field:SerializedName("token")
	val token: String?
) : Parcelable
