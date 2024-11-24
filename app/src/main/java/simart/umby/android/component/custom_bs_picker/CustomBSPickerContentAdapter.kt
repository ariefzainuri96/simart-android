package simart.umby.android.component.custom_bs_picker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.databinding.CustomBsPickerContentItemBinding

interface CustomBSPickerContentAdapterInterface {
    fun setDataContent(viewHolder: CustomBSPickerContentAdapter.ViewHolder, position: Int)
}

class CustomBSPickerContentAdapter: RecyclerView.Adapter<CustomBSPickerContentAdapter.ViewHolder>() {
    private var customBSPickerContentAdapterInterface: CustomBSPickerContentAdapterInterface? = null
    private var dataContent = mutableListOf<Any>()

    inner class ViewHolder(val binding: CustomBsPickerContentItemBinding): RecyclerView.ViewHolder(binding
        .root) {
        fun setData(data: String) {
            binding.content.text = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = CustomBsPickerContentItemBinding.inflate(
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
        customBSPickerContentAdapterInterface?.setDataContent(holder, position)
    }

    override fun getItemCount(): Int = dataContent.count()

    fun setInterface(customBSPickerContentAdapterInterface: CustomBSPickerContentAdapterInterface) {
        this.customBSPickerContentAdapterInterface = customBSPickerContentAdapterInterface
    }

    fun updateData(data: List<Any>) {
        dataContent.addAll(data)
        notifyDataSetChanged()
    }
}