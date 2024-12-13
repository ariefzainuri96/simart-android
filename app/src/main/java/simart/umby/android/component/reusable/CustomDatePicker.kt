package simart.umby.android.component.reusable

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import simart.umby.android.databinding.CustomDatePickerBinding
import java.util.Calendar

interface CustomDatePickerInterface {
    fun onDateSelected(value: String)
}

class CustomDatePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomDatePickerBinding =
        CustomDatePickerBinding.inflate(LayoutInflater.from(context), this, true)
    private var customDatePickerInterface: CustomDatePickerInterface? = null
    private var selectedCalendar: Calendar = Calendar.getInstance()

    init {
        binding.datePickerLayout.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val day = selectedCalendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val date = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                customDatePickerInterface?.onDateSelected(date)
                selectedCalendar.set(selectedYear, selectedMonth, selectedDayOfMonth)
                binding.date.text = date
            }, year, month, day )
        datePickerDialog.show()
    }

    fun setTitle(value: String) {
        binding.title.text = value
    }

    fun setSelectedDate(calendar: Calendar) {
        selectedCalendar = calendar
        binding.date.text = "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
    }

    fun setError(message: String?) {
        binding.error.text = message
        binding.error.visibility = VISIBLE
    }

    fun handleAction(customDatePickerInterface: CustomDatePickerInterface) {
        this.customDatePickerInterface = customDatePickerInterface
    }
}