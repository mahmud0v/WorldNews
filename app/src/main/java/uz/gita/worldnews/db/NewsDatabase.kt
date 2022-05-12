package uz.gita.worldnews.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.worldnews.models.NewsResponse

@Database(
    entities = [NewsResponse::class],
    version = 1
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

    companion object {
        var instance:NewsDatabase? = null

        fun initDb(context: Context){
            instance = Room.databaseBuilder(context,NewsDatabase::class.java,"News.db")
                       .build()

        }
    }
}