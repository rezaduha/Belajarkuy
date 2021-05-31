package com.belajarkuy.app.view

interface GeneralView {
    fun success(response: Any)
    fun error(error: Throwable?)
    fun showLoading()
    fun hideLoading()
    fun empty()
}