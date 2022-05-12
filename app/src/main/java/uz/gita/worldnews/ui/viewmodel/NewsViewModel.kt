package uz.gita.worldnews.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.gita.worldnews.models.NewsResponse
import uz.gita.worldnews.repository.NewsRepository
import uz.gita.worldnews.utils.Resource

class NewsViewModel :ViewModel() {

    private val _liveData = MutableLiveData<Resource<NewsResponse>>()
    val liveData: LiveData<Resource<NewsResponse>> = _liveData


    fun getNews(context: Context, country:String) = viewModelScope.launch {
        val repository = NewsRepository(context)
        val response = repository.getAllNews(country)
        if (response.isSuccessful) {
            _liveData.postValue(Resource.Success(response.body()!!))
        } else {
            _liveData.postValue(Resource.Error(response.body()!!, response.message()))
        }
    }

      }

