package com.belajarkuy.app.data.presenter

import com.belajarkuy.app.data.model.AuthRequest
import com.belajarkuy.app.data.model.ModuleRequest
import com.belajarkuy.app.data.network.ApiConfig
import com.belajarkuy.app.view.GeneralView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainPresenter(private val view: GeneralView) {

    private val api = ApiConfig.getApiService()
    private val newsApi = ApiConfig.getNewsApiService()

    fun continueWithGoogle(authRequest: AuthRequest) {
        CoroutineScope(Dispatchers.Main).launch {
            val data = api.login(authRequest)
            try {
                view.success(data)
            }catch (e: Exception) {
                view.error(e)
            }
            view.hideLoading()
        }
    }

    fun getCompetency(userId: String) {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val data = api.getCompetency(userId)
            try {
                view.success(data)
                if (data.results.isEmpty()) {
                    view.empty()
                }
            } catch (e: Exception) {
                view.error(e)
            }
            view.hideLoading()
        }
    }

    fun getAllModule() {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val data = api.getAllModule()
            try {
                view.success(data)
            } catch (e: Exception) {
                view.error(e)
            }
            view.hideLoading()
        }
    }

    fun getModuleById(moduleId: Int) {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val data = api.getModuleById(moduleId)
            try {
                view.success(data)
            } catch (e: Exception) {
                view.error(e)
            }
            view.hideLoading()
        }
    }

    fun getRecommendationBySubject(userId: Int, subject: String) {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val data = api.getRecommendationBySubject(userId, subject)
            try {
                view.success(data)
            } catch (e: Exception) {
                view.error(e)
            }
            view.hideLoading()
        }
    }

    fun submitAnswer(userId: String, moduleRequest: List<ModuleRequest>) {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val data = api.submitAnswer(userId, moduleRequest)
            try {
                view.success(data)
            } catch (e: Exception) {
                view.error(e)
            }
            view.hideLoading()
        }
    }

    fun getNews() {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val data = newsApi.getNews()
            try {
                view.success(data)
            } catch (e: Exception) {
                view.error(e)
            }
            view.hideLoading()
        }
    }
}