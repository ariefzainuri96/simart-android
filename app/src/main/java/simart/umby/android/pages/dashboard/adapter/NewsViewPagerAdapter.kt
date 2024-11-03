package simart.umby.android.pages.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.databinding.NewsItemBinding
import simart.umby.android.pages.dashboard.model.NewsModel

class NewsViewPagerAdapter(private val news: List<NewsModel>):
    RecyclerView.Adapter<NewsViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(data: NewsModel) {
            binding.title.text = data.title
            binding.date.text = data.date
            binding.description.text = data.description
        }
    }

    override fun getItemCount(): Int = news.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.setData(news[position])
    }
}