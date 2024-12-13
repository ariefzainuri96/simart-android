package simart.umby.android.adapter.manajemen_aset.tambah_peminjaman_aset

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.databinding.DetailPeminjamanItemBinding
import simart.umby.android.model.manajemen_aset.DetailPeminjamanAsetForm

class DetailPeminjamanAdapter: RecyclerView.Adapter<DetailPeminjamanAdapter.ViewHolder>() {
    private var data = mutableListOf<DetailPeminjamanAsetForm>()

    fun updateData(data: DetailPeminjamanAsetForm) {
        this.data.add(data)
        notifyItemInserted(this.data.size)
    }

    inner class ViewHolder(val binding: DetailPeminjamanItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(data: DetailPeminjamanAsetForm) {
            binding.content.text = data.namaAset
            binding.unit.text = "${data.jumlah} Unit"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DetailPeminjamanItemBinding.inflate(
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
    }

    override fun getItemCount(): Int = data.size
}