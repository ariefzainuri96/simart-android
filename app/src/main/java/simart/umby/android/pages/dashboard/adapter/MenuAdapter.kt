package simart.umby.android.pages.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.databinding.MenuItemBinding
import simart.umby.android.pages.dashboard.model.MenuModel

// RecyclerView.Adapter<NewsViewPagerAdapter.ViewPagerViewHolder>()
class MenuAdapter(
    private val menus: List<MenuModel>
): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private var onClickListener: OnClickListener? = null

    inner class MenuViewHolder(val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(data: MenuModel) {
            binding.title.text = data.title
            binding.cardImage.setCardBackgroundColor(data.color)
            binding.image.setImageResource(data.icon)
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(0, 16, 0, 0)
            binding.root.layoutParams = layoutParams


        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuViewHolder {
        val binding = MenuItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            onClickListener?.onClick(position, menus[position])
        }

        holder.setData(menus[position])
    }

    // Set the click listener for the adapter
    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    // Interface for the click listener
    interface OnClickListener {
        fun onClick(position: Int, model: MenuModel)
    }

    override fun getItemCount(): Int = menus.count()
}