package com.belajarkuy.app.data.presenter

import android.content.Context
import com.belajarkuy.app.data.model.AuthRequest
import com.belajarkuy.app.data.model.ModuleRequest
import com.belajarkuy.app.data.network.ApiConfig
import com.belajarkuy.app.view.GeneralView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllPresenter(private val view: GeneralView, private val context: Context) {

    private val api = ApiConfig.getApiService(context)

    fun continueWithGoogle(authRequest: AuthRequest) {
        CoroutineScope(Dispatchers.Main).launch {
            val request = api.login(authRequest)
            try {
                val response = request
                view.success(response)
            }catch (e: Exception) {
                view.error(e)
            }
        }
    }

    fun getCompetency(userId: Int) {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val request = api.getCompetency(userId)
            try {
                val response = request
                view.success(response)
            } catch (e: Exception) {
                view.error(e)
            }
        }
    }

    fun getAllModule() {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val request = api.getAllModule()
            try {
                val response = request
                view.success(response)
            } catch (e: Exception) {
                view.error(e)
            }
        }
    }

    fun getModuleById(moduleId: Int) {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val request = api.getModuleById(moduleId)
            try {
                val response = request
                view.success(response)
            } catch (e: Exception) {
                view.error(e)
            }
        }
    }

    fun getRecommendationBySubject(userId: Int, subject: String) {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val request = api.getRecommendationBySubject(userId, subject)
            try {
                val response = request
                view.success(response)
            } catch (e: Exception) {
                view.error(e)
            }
        }
    }

    fun submitAnswer(moduleId: Int, moduleRequest: List<ModuleRequest>) {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val request = api.submitAnswer(moduleId, moduleRequest)
            try {
                val response = request
                view.success(response)
            } catch (e: Exception) {
                view.error(e)
            }
        }
    }
}