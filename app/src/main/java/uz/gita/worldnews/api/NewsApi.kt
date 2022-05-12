package uz.gita.worldnews.api

import androidx.lifecycle.LiveData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.worldnews.models.NewsResponse
import uz.gita.worldnews.utils.Constants.Companion.API_KEY

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = API_KEY
    ) : Response<NewsResponse>



}