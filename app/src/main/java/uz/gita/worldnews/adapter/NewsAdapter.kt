package uz.gita.worldnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.worldnews.R
import uz.gita.worldnews.models.NewsData

class NewsAdapter(private val listener: ItemClickListener) : RecyclerView.Adapter<NewsViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<NewsData>() {

        override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData) =
            oldItem.url == newItem.url


        override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData) =
            oldItem == newItem

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val imageView:ImageView = holder.itemView.findViewById(R.id.news_image)
        val title:TextView = holder.itemView.findViewById(R.id.title_id)
        val date:TextView = holder.itemView.findViewById(R.id.date_id)
        if (differ.currentList[position].urlToImage == null){
            imageView.setImageResource(R.drawable.news_image)
        } else{
            Glide.with(holder.itemView).load(differ.currentList[position].urlToImage).into(imageView)
        }
        differ.currentList[position].also {
            title.text = it.title
            date.text = it.publishedAt.substring(0,10)
        }
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(differ.currentList[position])
        }
    }

    override fun getItemCount() = differ.currentList.size
}