package simart.umby.android.pages.manajemen_inventaris.data_barang_aset

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.R
import simart.umby.android.databinding.DataBarangAsetItemBinding
import simart.umby.android.pages.manajemen_inventaris.data_barang_aset.model.DataBarangAsetModel
import simart.umby.android.utils.toPx

class DataBarangAsetAdapter(val context: Context, private var listDataBarang: List<DataBarangAsetModel>): RecyclerView.Adapter<DataBarangAsetAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    inner class ViewHolder(val binding: DataBarangAsetItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(data: DataBarangAsetModel) {
            binding.title.text = data.namaAset
            binding.codeBarang.text = data.noInventaris
            binding.keterangan.text = "${data.jumlahAset} unit . ${data.sumberAset}"
            binding.deskripsi.text = data.deskripsiAset
            binding.spesifikasi.text = data.spesifikasi

            binding.expandableView.setExtraMargin(16)
            binding.expandableViewHeader.setOnClickListener {
                binding.expandableView.setExpanded(!binding.expandableView.isExpanded)
                binding.expandableViewIcon.setImageResource(if (binding.expandableView.isExpanded) R.drawable.ic_collapse else R.drawable.ic_expand)
            }

            binding.expandableDeskripsi.setExtraMargin(16)
            binding.expandableDeskripsiHeader.setOnClickListener {
                binding.expandableDeskripsi.setExpanded(!binding.expandableDeskripsi.isExpanded)
                binding.expandableDeskripsiIcon.setImageResource(if (binding.expandableDeskripsi.isExpanded) R.drawable.ic_collapse else R.drawable.ic_expand)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DataBarangAsetItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            onClickListener?.onClick(position, listDataBarang[position])
        }

        holder.binding.icMenu.setOnClickListener {
            showPopupMenu(it)
        }

        val cardView = holder.binding.root
        (cardView.layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin = if (position == 0) context.toPx(16) else context.toPx(8)
        (cardView.layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin = if (position == listDataBarang.count() - 1) context.toPx(16) else 0
        cardView.requestLayout()

        holder.setData(listDataBarang[position])
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(context, view)

        popupMenu.menuInflater.inflate(R.menu.common_action_menu, popupMenu.menu)

        popupMenu.setForceShowIcon(true)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            if (menuItem.itemId == R.id.menuEdit) {
                println("edit clicked")
            } else if (menuItem.itemId == R.id.menuDelete) {
                println("delete clicked")
            }

            true
        }

        popupMenu.show()
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // Interface for the click listener
    interface OnClickListener {
        fun onClick(position: Int, model: DataBarangAsetModel)
    }

    override fun getItemCount(): Int = listDataBarang.count()
}