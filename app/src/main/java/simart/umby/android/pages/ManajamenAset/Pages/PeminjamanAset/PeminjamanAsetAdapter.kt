package simart.umby.android.pages.ManajamenAset.Pages.PeminjamanAset

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.R
import simart.umby.android.databinding.PeminjamanAsetItemBinding
import simart.umby.android.pages.ManajamenAset.Model.PeminjamanAsetModel
import simart.umby.android.utils.toPx

class PeminjamanAsetAdapter(private val context: Context): RecyclerView
    .Adapter<PeminjamanAsetAdapter.ViewHolder>() {
    private var list = mutableListOf<PeminjamanAsetModel>()

    inner class ViewHolder(val binding: PeminjamanAsetItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(data: PeminjamanAsetModel) {
            binding.namaPeminjam.text = data.namaPeminjam
            binding.noPeminjaman.text = data.noPeminjaman
            binding.infoPeminjaman.text = "${data.tipePeminjam} . ${data.noTeleponPeminjam}"
            binding.tanggalPinjam.apply {
                setContent("Tanggal Pinjam", data.tanggalPinjam)
                setTextLayout(contentSize = 12F)
            }
            binding.statusApproval.apply {
                setContent("Status Approval", data.statusApproval)
                setTextLayout(contentSize = 12F, contentColor = R.color.red2, textAlignment =
                TextView.TEXT_ALIGNMENT_TEXT_END)
            }
            binding.tanggalKembali.apply {
                setContent("Tanggal Kembali", data.tanggalKembali)
                setTextLayout(contentSize = 12F)
            }
            binding.statusPeminjaman.apply {
                setContent("Status Peminjaman", data.statusPeminjaman)
                setTextLayout(contentSize = 12F, contentColor = R.color.green3, textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PeminjamanAsetItemBinding.inflate(
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
            list.count() - 1) context.toPx(16) else 0
        cardView.requestLayout()

        holder.setData(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateData(data: List<PeminjamanAsetModel>) {
        val range = list.size

        list.clear()
        list.addAll(data)
        notifyItemRangeInserted(range, list.size)
    }
}