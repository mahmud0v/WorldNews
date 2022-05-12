package uz.gita.worldnews.repository

import android.content.Context
import retrofit2.Retrofit
import retrofit2.create
import uz.gita.worldnews.api.NewsApi
import uz.gita.worldnews.api.NewsApiInstance

class NewsRepository(private val context:Context) {

    private val retrofit = NewsApiInstance.getRetrofit(context).create(NewsApi::class.java)

    suspend fun getAllNews(country:String) = retrofit.getNews(country)




}