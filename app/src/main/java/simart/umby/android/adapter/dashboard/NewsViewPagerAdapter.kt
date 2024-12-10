package simart.umby.android.adapter.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.data.response.PengumumanData
import simart.umby.android.databinding.NewsItemBinding

class NewsViewPagerAdapter(private val news: List<PengumumanData>):
    RecyclerView.Adapter<NewsViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(data: PengumumanData) {
            binding.title.text = data.title
            binding.date.text = data.date
            binding.description.text = data.pengumuman
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