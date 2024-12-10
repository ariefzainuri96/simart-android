package simart.umby.android.component.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import simart.umby.android.databinding.CustomCheckboxBinding

interface CustomCheckboxInterface {
    fun onCheckedChange(value: Boolean)
}

class CustomCheckbox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomCheckboxBinding =
        CustomCheckboxBinding.inflate(LayoutInflater.from(context), this, true)
    private var customCheckboxInterface: CustomCheckboxInterface? = null

    init {
        binding.checkboxLayout.setOnClickListener {
            binding.checkbox.isChecked = !binding.checkbox.isChecked
            customCheckboxInterface?.onCheckedChange(binding.checkbox.isChecked)
        }
    }

    fun setLabel(value: String) {
        binding.label.text = value
    }

    fun handleAction(customCheckboxInterface: CustomCheckboxInterface) {
        this.customCheckboxInterface = customCheckboxInterface
    }
}