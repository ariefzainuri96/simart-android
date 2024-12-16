package simart.umby.android.adapter.manajemen_aset.tambah_peminjaman_aset

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.data.response.HistoryPersetujuanData
import simart.umby.android.databinding.HistoryPersetujuanItemBinding

class HistoryPersetujuanAdapter: RecyclerView.Adapter<HistoryPersetujuanAdapter.ViewHolder>() {
    private var data = mutableListOf<HistoryPersetujuanData>()

    fun updateData(data: List<HistoryPersetujuanData>) {
        val previousSize = this.data.size
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(previousSize, this.data.size)
    }

    inner class ViewHolder(val binding: HistoryPersetujuanItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(data: HistoryPersetujuanData) {
            binding.disetujuiOleh.text = data.disetujuiOleh
            binding.status.text = data.status
            binding.tanggal.text = data.tanggal
            binding.keterangan.text = data.keterangan
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = HistoryPersetujuanItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.setData(data[position])

        holder.binding.keteranganLayout.setOnClickListener {
            holder.binding.keteranganContent.toggleExpanded()

            val rotationAngle = if (holder.binding.keteranganContent.isExpanded) 180f else 0f

            holder.binding.keteranganIcon.animate()
                .rotation(rotationAngle)
                .setDuration(300L) // Animation duration in milliseconds
                .start()
        }
    }

    override fun getItemCount(): Int = data.size
}