package com.example.androidtutorial.pages.dashboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtutorial.data.repository.DashboardRepository
import com.example.androidtutorial.pages.dashboard.model.NewsModel
import com.example.androidtutorial.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor (
    private val repository: DashboardRepository,
    private val appContext: Application
): ViewModel() {
    var news = mutableListOf<NewsModel>()
        private set

    private var _newsState = MutableStateFlow(RequestState.IDLE)
    val newsState = _newsState.asStateFlow()

    private fun getNews() {
        val handler = CoroutineExceptionHandler {_, throwable ->
            println("Error happening: $throwable")

            _newsState.value = RequestState.ERROR
        }

        _newsState.value = RequestState.LOADING

        viewModelScope.launch(handler) {
            withContext(Dispatchers.IO) {
                val response = (async { repository.doNetworkCall(1) }).await()

                if (response.isSuccessful) {
                    response.body()?.results?.let { planets ->
                        news.addAll(planets.map { item ->
                            NewsModel(item?.name ?: "", item?.created ?: "", "${item?.orbitalPeriod} - ${item?.rotationPeriod} - ${item?.surfaceWater}")
                        })
                    }
                } else {
                    println("Failed get planets, Error: ${response.errorBody()}")
                }

                _newsState.value = if (response.isSuccessful) RequestState.SUCCESS else RequestState.ERROR
            }
        }
    }

    init {
        getNews()
    }
}