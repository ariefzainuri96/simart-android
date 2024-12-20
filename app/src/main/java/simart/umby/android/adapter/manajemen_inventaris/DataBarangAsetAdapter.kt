package simart.umby.android.adapter.manajemen_inventaris

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.R
import simart.umby.android.databinding.DataBarangAsetItemBinding
import simart.umby.android.model.manajemen_inventaris.DataBarangAsetModel
import simart.umby.android.utils.toPx

// Interface for the click listener
interface DataBarangAsetAdapterInterface {
    fun onEditClick(position: Int, model: DataBarangAsetModel)
    fun onDeleteClick(position: Int, model: DataBarangAsetModel)
}

class DataBarangAsetAdapter(
    val context: Context,
    private var listDataBarang: List<DataBarangAsetModel>
) : RecyclerView.Adapter<DataBarangAsetAdapter.ViewHolder>() {

    private var dataBarangAsetAdapterInterface: DataBarangAsetAdapterInterface? = null

    inner class ViewHolder(val binding: DataBarangAsetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: DataBarangAsetModel) {
            binding.title.text = data.namaAset
            binding.codeBarang.text = data.noInventaris
            binding.keterangan.text = "${data.jumlahAset} unit . ${data.sumberAset}"
            binding.deskripsi.text = data.deskripsiAset
            binding.spesifikasi.text = data.spesifikasi
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
        holder.binding.expandableSpesifikasi.setExtraMargin(16)
        holder.binding.expandableSpesifikasiHeader.setOnClickListener {
            holder.binding.expandableSpesifikasi.toggleExpanded()
            holder.binding.expandableSpesifikasiIcon.setImageResource(
                if (holder.binding.expandableSpesifikasi.isExpanded) R
                    .drawable.ic_collapse else R.drawable.ic_expand
            )
        }

        holder.binding.expandableDeskripsi.setExtraMargin(16)
        holder.binding.expandableDeskripsiHeader.setOnClickListener {
            holder.binding.expandableDeskripsi.toggleExpanded()
            holder.binding.expandableDeskripsiIcon.setImageResource(if (holder.binding.expandableDeskripsi.isExpanded) R.drawable.ic_collapse else R.drawable.ic_expand)
        }

        holder.binding.icMenu.setOnClickListener {
            showPopupMenu(it, position, listDataBarang[position])
        }

        val cardView = holder.binding.root
        (cardView.layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin =
            if (position == 0) context.toPx(16) else context.toPx(8)
        (cardView.layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin =
            if (position == listDataBarang.count() - 1) context.toPx(16) else 0
        cardView.requestLayout()

        holder.setData(listDataBarang[position])
    }

    private fun showPopupMenu(view: View, position: Int, model: DataBarangAsetModel) {
        val popupMenu = PopupMenu(context, view)

        popupMenu.menuInflater.inflate(R.menu.common_action_menu, popupMenu.menu)

        popupMenu.setForceShowIcon(true)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            if (menuItem.itemId == R.id.menuEdit) {
                dataBarangAsetAdapterInterface?.onEditClick(position, model)
            } else if (menuItem.itemId == R.id.menuDelete) {
                dataBarangAsetAdapterInterface?.onDeleteClick(position, model)
            }

            true
        }

        popupMenu.show()
    }

    fun setInterface(dataBarangAsetAdapterInterface: DataBarangAsetAdapterInterface) {
        this.dataBarangAsetAdapterInterface = dataBarangAsetAdapterInterface
    }

    override fun getItemCount(): Int = listDataBarang.count()
}