package simart.umby.android.pages.dashboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import simart.umby.android.R
import simart.umby.android.data.repository.DashboardRepository
import simart.umby.android.pages.dashboard.model.MenuModel
import simart.umby.android.pages.dashboard.model.NewsModel
import simart.umby.android.utils.RequestState
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor (
    private val repository: DashboardRepository,
    private val appContext: Application
): ViewModel() {
    var news = listOf<NewsModel>(); private set

    private var _newsState = MutableStateFlow(RequestState.IDLE)
    val newsState = _newsState.asStateFlow()

    val menus = listOf<MenuModel>(
        MenuModel("Manajemen Inventaris", R.drawable.ic_manajemen_inventaris, appContext.applicationContext.getColor(R.color.blue2)),
        MenuModel("Manajemen Barang Pakai Habis", R.drawable.manajemen_barang_habis_pakai, appContext.applicationContext.getColor(R.color.purple2)),
        MenuModel("Manajemen Aset", R.drawable.manajemen_aset, appContext.applicationContext.getColor(R.color.blue3)),
        MenuModel("Task Approval", R.drawable.ic_check_square, appContext.applicationContext.getColor(R.color.green)),
    )

    fun getNews() {
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
                        news = planets.map { item ->
                            NewsModel(
                                item?.name ?: "",
                                item?.created ?: "",
                                "${item?.orbitalPeriod} - ${item?.rotationPeriod} - ${item?.surfaceWater}"
                            )
                        }
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