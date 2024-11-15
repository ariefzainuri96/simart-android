package simart.umby.android.pages.manajemen_inventaris.edit_data_barang_aset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import simart.umby.android.component.CustomDatePickerInterface
import simart.umby.android.component.OnItemSelectedListener
import simart.umby.android.databinding.EditDataBarangAsetBsBinding

class EditDataBarangAsetBS: BottomSheetDialogFragment() {
    private lateinit var binding: EditDataBarangAsetBsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditDataBarangAsetBsBinding.inflate(inflater)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { sheet ->
                val behavior = BottomSheetBehavior.from(sheet)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED // Set to expanded by default
                behavior.isDraggable = false

                sheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                sheet.requestLayout()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.appbar.setIcCloseClick {
            dismiss()
        }

        binding.dropdown1.apply {
            setTitle("Vendor")
            setDropdownText("Pilih Vendor")
            setDropdown(listOf(
                "ABC", "DEF"
            ))
            handleOnItemSelected(object : OnItemSelectedListener {
                override fun onItemSelected(value: String) {
                    println("you select $value")
                }
            })
        }

        binding.datePicker.apply {
            setTitle("Tanggal Akuisisi")
            handleAction(object : CustomDatePickerInterface {
                override fun onDateSelected(value: String) {
                    println("selected Date $value")
                }
            })
        }

    }
}