package simart.umby.android.pages.manajemen_inventaris.edit_data_barang_aset

import simart.umby.android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import simart.umby.android.component.CustomCheckboxInterface
import simart.umby.android.component.CustomDatePickerInterface
import simart.umby.android.component.OnItemSelectedListener
import simart.umby.android.databinding.EditDataBarangAsetBsBinding
import simart.umby.android.pages.manajemen_inventaris.data_barang_aset.model.DataBarangAsetModel

@AndroidEntryPoint
class EditDataBarangAsetBS(
    private val dataBarang: DataBarangAsetModel
): BottomSheetDialogFragment() {
    private val viewModel: EditDataBarangAsetVM by viewModels()

    private lateinit var binding: EditDataBarangAsetBsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditDataBarangAsetBsBinding.inflate(inflater)

        viewModel.initForm(dataBarang)

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

        binding.tanggalAkuisisi.apply {
            setTitle("Tanggal Akuisisi")
            handleAction(object : CustomDatePickerInterface {
                override fun onDateSelected(value: String) {
                    println("selected Date $value")
                }
            })
        }

        binding.tanggalDepresiasi.apply {
            setTitle("Tanggal Akhir")
            handleAction(object : CustomDatePickerInterface {
                override fun onDateSelected(value: String) {
                    println("selected Date $value")
                }
            })
        }

        binding.noInventaris.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(noInventaris = text.toString()) }
            }
        }

        binding.namaAset.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(namaAset = text.toString()) }
            }
        }

        binding.deskripsiAset.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(deskripsiAset = text.toString()) }
            }
        }

        binding.spesifikasiAset.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(spesifikasi = text.toString()) }
            }
        }

        binding.isAsetSPK.handleAction(object: CustomCheckboxInterface {
            override fun onCheckedChange(value: Boolean) {
                viewModel.updateForm { copy(isAsetSPK = value) }
            }
        })

        binding.noSPK.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(noSPK = text.toString()) }
            }
        }

        binding.vendor.apply {
            setTitle("Vendor")
            setDropdownText("Pilih Vendor")
            setDropdown(listOf(
                "ABC", "DEF"
            ))

            handleOnItemSelected(object : OnItemSelectedListener {
                override fun onItemSelected(value: String) {
                    viewModel.updateForm { copy(vendor = value) }
                }
            })
        }

        binding.tipeAsetRadio.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radioATK) {
                viewModel.updateForm { copy(tipeAset = "ATK") }
            } else if (checkedId == R.id.radioAset) {
                viewModel.updateForm { copy(tipeAset = "Aset") }
            }
        }

        binding.kategoriAset.apply {
            setTitle("Kategori Aset")
            setDropdownText("Pilih Kategori Aset")
            setDropdown(listOf(
                "ABC", "DEF"
            ))

            handleOnItemSelected(object : OnItemSelectedListener {
                override fun onItemSelected(value: String) {
                    viewModel.updateForm { copy(kategoriAset = value) }
                }
            })
        }

        binding.subKategoriAset.apply {
            setTitle("Sub Kategori Aset")
            setDropdownText("Pilih Sub Kategori Aset")
            setDropdown(listOf(
                "ABC", "DEF"
            ))
            handleOnItemSelected(object : OnItemSelectedListener {
                override fun onItemSelected(value: String) {
                    viewModel.updateForm { copy(subKategoriAset = value) }
                }
            })
        }

        binding.kampus.apply {
            setTitle("Kampus")
            setDropdownText("Pilih Kampus")
            setDropdown(listOf(
                "ABC", "DEF"
            ))

            handleOnItemSelected(object : OnItemSelectedListener {
                override fun onItemSelected(value: String) {
                    viewModel.updateForm { copy(kampus = value) }
                }
            })
        }

        binding.ruang.apply {
            setTitle("Ruang")
            setDropdownText("Pilih Ruang")
            setDropdown(listOf(
                "ABC", "DEF"
            ))

            handleOnItemSelected(object : OnItemSelectedListener {
                override fun onItemSelected(value: String) {
                    viewModel.updateForm { copy(ruang = value) }
                }
            })
        }

        binding.convidentality.apply {
            setTitle("Convisionality")
            setDropdownText("Pilih Convisionality")
            setDropdown(listOf(
                "ABC", "DEF"
            ))

            handleOnItemSelected(object : OnItemSelectedListener {
                override fun onItemSelected(value: String) {
                    viewModel.updateForm { copy(convidentality = value) }
                }
            })
        }

        binding.integrity.apply {
            setTitle("Integrity")
            setDropdownText("Pilih Integrity")
            setDropdown(listOf(
                "ABC", "DEF"
            ))

            handleOnItemSelected(object : OnItemSelectedListener {
                override fun onItemSelected(value: String) {
                    viewModel.updateForm { copy(integrity = value) }
                }
            })
        }

        binding.availability.apply {
            setTitle("Availability")
            setDropdownText("Pilih Availability")
            setDropdown(listOf(
                "ABC", "DEF"
            ))

            handleOnItemSelected(object : OnItemSelectedListener {
                override fun onItemSelected(value: String) {
                    viewModel.updateForm { copy(availability = value) }
                }
            })
        }

        binding.asetTerdepresiasiRadio.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.asetTerdepresiasiYa) {
                viewModel.updateForm { copy(asetTerdepresiasi = "Ya") }
            } else if (checkedId == R.id.asetTerdepresiasiTidak) {
                viewModel.updateForm { copy(asetTerdepresiasi = "Tidak") }
            }
        }

        binding.sumberAset.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(sumberAset = text.toString()) }
            }
        }

        binding.jumlahAset.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(jumlahAset = text.toString()) }
            }
        }

        binding.nvb.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(nvb = text.toString()) }
            }
        }

        binding.statusAset.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(statusAset = text.toString()) }
            }
        }

        binding.btnSimpan.setOnClickListener {
            viewModel.saveData()
        }
    }
}