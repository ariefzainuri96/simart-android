package simart.umby.android.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import simart.umby.android.databinding.CustomDropdownBinding

class CustomDropdown @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomDropdownBinding =
        CustomDropdownBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, listOf<String>())
        // get reference to the autocomplete text view

        // set adapter to the autocomplete tv to the arrayAdapter
        binding.dropdownMenu.setAdapter(arrayAdapter)
    }

    fun setDropdown(data: List<String>) {
        val adapter = binding.dropdownMenu.adapter as ArrayAdapter<String>
        adapter.clear()
        adapter.addAll(data)
        adapter.notifyDataSetChanged()
    }

    fun setTitle(value: String) {
        binding.title.text = value
    }

    fun setError(message: String?) {
        binding.dropdownLayout.isErrorEnabled = message != null
        binding.dropdownLayout.error = message
    }
}