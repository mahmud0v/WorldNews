package uz.gita.worldnews.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.worldnews.models.NewsResponse

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNews(data: NewsDao)

    @Query("Select *from NewsData")
    fun getAllNews(): LiveData<List<NewsResponse>>



}