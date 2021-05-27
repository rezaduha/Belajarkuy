package com.belajarkuy.app.view

interface GeneralView {
    fun showLoading()
    fun error(error: Throwable?)
    fun success(response: Any)
    fun hideLoading()
}