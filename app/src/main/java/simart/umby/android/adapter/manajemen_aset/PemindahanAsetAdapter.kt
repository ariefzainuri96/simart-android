package simart.umby.android.adapter.manajemen_aset

import android.content.Context
import android.view.LayoutInflater
import android.view.View.TEXT_ALIGNMENT_TEXT_END
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.data.response.PemindahanAsetData
import simart.umby.android.databinding.PemindahanAsetItemBinding
import simart.umby.android.utils.toPx

class PemindahanAsetAdapter(private val context: Context): RecyclerView.Adapter<PemindahanAsetAdapter
    .ViewHolder>() {
    private var data = mutableListOf<PemindahanAsetData>()

    fun updateData(data: List<PemindahanAsetData>) {
        var previousSize = this.data.size
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(previousSize, this.data.size)
    }

    inner class ViewHolder(val binding: PemindahanAsetItemBinding): RecyclerView.ViewHolder(binding.root)  {
        fun setData(data: PemindahanAsetData) {
            binding.noPemindahan.text = data.noPemindahan
            binding.tanggal.text = data.tanggal
            binding.status.text = "Oke"
            binding.lokasiAsal.apply {
                setContent("Lokasi Asal", data.lokasiAsal ?: "")
            }
            binding.lokasiTujuan.apply {
                setContent("Lokasi Tujuan", data.lokasiTujuan ?: "")
                setTextLayout(textAlignment = TEXT_ALIGNMENT_TEXT_END)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PemindahanAsetItemBinding.inflate(
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
        val cardView = holder.binding.root
        (cardView.layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin = if (position == 0) context.toPx(16) else context.toPx(8)
        (cardView.layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin = if (position ==
            data.count() - 1) context.toPx(16) else 0
        cardView.requestLayout()

        holder.setData(data[position])
    }

    override fun getItemCount(): Int = data.size
}