package uz.gita.worldnews.models

data class NewsResponse(
    val status:String,
    val totalResults:Int,
    val articles:List<NewsData>
)