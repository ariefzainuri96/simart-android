package simart.umby.android.component.reusable.custom_bs_picker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import simart.umby.android.databinding.CustomBottomSheetPickerBinding

interface CustomBottomSheetPickerInterface {
    fun showBottomSheet()
}

class CustomBottomSheetPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var binding: CustomBottomSheetPickerBinding =
        CustomBottomSheetPickerBinding.inflate(LayoutInflater.from(context), this, true)

    private var customBottomSheetPickerInterface: CustomBottomSheetPickerInterface? = null

    init {
        binding.pickerLayout.setOnClickListener {
            customBottomSheetPickerInterface?.showBottomSheet()
        }
    }

    fun setTitle(value: String) {
        binding.title.text = value
    }

    fun setContent(value: String) {
        binding.content.text = value
    }

    fun setAction(customBottomSheetPickerInterface: CustomBottomSheetPickerInterface) {
        this.customBottomSheetPickerInterface = customBottomSheetPickerInterface
    }
}