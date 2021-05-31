package com.belajarkuy.app.data.presenter

import android.content.Context
import com.belajarkuy.app.data.model.AuthRequest
import com.belajarkuy.app.data.model.ModuleRequest
import com.belajarkuy.app.data.network.ApiConfig
import com.belajarkuy.app.view.GeneralView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainPresenter(private val view: GeneralView, private val context: Context) {

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
            view.hideLoading()
        }
    }

//    fun getCompetency(userId: Int) {
//        view.showLoading()
//        CoroutineScope(Dispatchers.Main).launch {
//            val request = api.getCompetency(userId)
//            try {
//                val response = request
//                view.success(response)
//                if (response.results.isEmpty()) {
//                    view.empty()
//                }
//            } catch (e: Exception) {
//                view.error(e)
//            }
//            view.hideLoading()
//        }
//    }
//
//    fun getAllModule() {
//        view.showLoading()
//        CoroutineScope(Dispatchers.Main).launch {
//            val request = api.getAllModule()
//            try {
//                val response = request
//                view.success(response)
//            } catch (e: Exception) {
//                view.error(e)
//            }
//            view.hideLoading()
//        }
//    }
//
//    fun getModuleById(moduleId: Int) {
//        view.showLoading()
//        CoroutineScope(Dispatchers.Main).launch {
//            val request = api.getModuleById(moduleId)
//            try {
//                val response = request
//                view.success(response)
//            } catch (e: Exception) {
//                view.error(e)
//            }
//            view.hideLoading()
//        }
//    }

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
            view.hideLoading()
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
            view.hideLoading()
        }
    }

    // just for testing
    fun getCompetency() {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val request = api.getCompetency()
            try {
                val response = request
                view.success(response)
                if (response.results.isEmpty()) {
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
            val request = api.getAllModule()
            try {
                val response = request
                view.success(response)
            } catch (e: Exception) {
                view.error(e)
            }
            view.hideLoading()
        }
    }

    fun getModuleById() {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val request = api.getModuleById()
            try {
                val response = request
                view.success(response)
            } catch (e: Exception) {
                view.error(e)
            }
        }
    }
}