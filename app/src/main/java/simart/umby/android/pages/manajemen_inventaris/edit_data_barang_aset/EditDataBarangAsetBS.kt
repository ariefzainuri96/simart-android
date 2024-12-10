package simart.umby.android.pages.manajemen_inventaris.edit_data_barang_aset

import simart.umby.android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import simart.umby.android.component.reusable.CustomCheckboxInterface
import simart.umby.android.component.reusable.CustomDatePickerInterface
import simart.umby.android.component.reusable.custom_bs_picker.CustomBSPickerContent
import simart.umby.android.component.reusable.custom_bs_picker.CustomBSPickerContentAdapter
import simart.umby.android.component.reusable.custom_bs_picker.CustomBSPickerContentInterface
import simart.umby.android.component.reusable.custom_bs_picker.CustomBottomSheetPickerInterface
import simart.umby.android.component.reusable.CustomCheckbox
import simart.umby.android.component.reusable.CustomDatePicker
import simart.umby.android.component.reusable.custom_bs_picker.CustomBottomSheetPicker
import simart.umby.android.databinding.EditDataBarangAsetBsBinding
import simart.umby.android.model.manajemen_inventaris.DataBarangAsetModel
import simart.umby.android.utils.RequestState
import simart.umby.android.utils.collectLatestLifeCycleFlow

@AndroidEntryPoint
class EditDataBarangAsetBS(
    private val dataBarang: DataBarangAsetModel
) : BottomSheetDialogFragment() {
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
            val bottomSheet =
                it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
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

        setupView()
    }

    fun setupView() {
        binding.appbar.apply {
            setIcCloseClick {
                dismiss()
            }

            setLayout("Edit Data Barang Aset")
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

        binding.isAsetSPK.handleAction(object : CustomCheckboxInterface {
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

            setContent("Pilih Vendor")

            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    if (viewModel.vendorState.value == RequestState.LOADING) return

                    val dialog = CustomBSPickerContent(
                        "Pilih Vendor",
                        object : CustomBSPickerContentInterface {
                            override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                                adapter as CustomBSPickerContentAdapter

                                collectLatestLifeCycleFlow(viewModel.vendorList) { newData ->
                                    adapter.updateData(newData)
                                }
                            }

                            override fun onItemClick(position: Int) {
                                setContent(viewModel.vendorList.value[position])
                            }

                            override fun onGetMoreData() {
                                viewModel.getVendor(isInitial = false)
                            }
                        })

                    dialog.show(childFragmentManager, CustomBSPickerContent::class.java.simpleName)
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

            setContent("Pilih Kategori Aset")

            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    if (viewModel.kategoriAsetState.value == RequestState.LOADING) return

                    val dialog = CustomBSPickerContent(
                        "Pilih Kategori Aset",
                        object : CustomBSPickerContentInterface {
                            override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                                adapter as CustomBSPickerContentAdapter

                                collectLatestLifeCycleFlow(viewModel.kategoriAsetList) { newData ->
                                    adapter.updateData(newData)
                                }
                            }

                            override fun onItemClick(position: Int) {
                                setContent(viewModel.kategoriAsetList.value[position])
                            }

                            override fun onGetMoreData() {
                                viewModel.getKategoriAset(isInitial = false)
                            }
                        })

                    dialog.show(childFragmentManager, CustomBSPickerContent::class.java.simpleName)
                }
            })
        }

        binding.subKategoriAset.apply {
            setTitle("Sub Kategori Aset")

            setContent("Pilih Sub Kategori Aset")

            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    if (viewModel.subKategoriAsetState.value == RequestState.LOADING) return

                    val dialog = CustomBSPickerContent(
                        "Pilih Vendor",
                        object : CustomBSPickerContentInterface {
                            override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                                adapter as CustomBSPickerContentAdapter

                                collectLatestLifeCycleFlow(viewModel.subKategoriAsetList) { newData ->
                                    adapter.updateData(newData)
                                }
                            }

                            override fun onItemClick(position: Int) {
                                setContent(viewModel.subKategoriAsetList.value[position])
                            }

                            override fun onGetMoreData() {
                                viewModel.getSubKategoriAset(isInitial = false)
                            }
                        })

                    dialog.show(childFragmentManager, CustomBSPickerContent::class.java.simpleName)
                }
            })
        }

        binding.location.apply {
            setTitle("Location")

            setContent("Pilih Location")

            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    if (viewModel.locationState.value == RequestState.LOADING) return

                    val dialog = CustomBSPickerContent(
                        "Pilih Location",
                        object : CustomBSPickerContentInterface {
                            override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                                adapter as CustomBSPickerContentAdapter

                                collectLatestLifeCycleFlow(viewModel.locationList) { newData ->
                                    adapter.updateData(newData)
                                }
                            }

                            override fun onItemClick(position: Int) {
                                setContent(viewModel.locationList.value[position])
                            }

                            override fun onGetMoreData() {
                                viewModel.getLocation(isInitial = false)
                            }
                        })

                    dialog.show(childFragmentManager, CustomBSPickerContent::class.java.simpleName)
                }
            })
        }

        binding.convidentality.apply {
            setTitle("Convidentality")

            setContent("Pilih Convidentality")

            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    if (viewModel.ciaState.value == RequestState.LOADING) return

                    val dialog = CustomBSPickerContent(
                        "Pilih Convidentality",
                        object : CustomBSPickerContentInterface {
                            override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                                adapter as CustomBSPickerContentAdapter

                                collectLatestLifeCycleFlow(viewModel.ciaList) { newData ->
                                    adapter.updateData(newData)
                                }
                            }

                            override fun onItemClick(position: Int) {
                                setContent(viewModel.ciaList.value[position])
                            }

                            override fun onGetMoreData() {
                                viewModel.getAvailability()
                            }
                        })

                    dialog.show(childFragmentManager, CustomBSPickerContent::class.java.simpleName)
                }
            })
        }

        binding.integrity.apply {
            setTitle("Integrity")

            setContent("Pilih Integrity")

            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    if (viewModel.ciaState.value == RequestState.LOADING) return

                    val dialog = CustomBSPickerContent(
                        "Pilih Integrity",
                        object : CustomBSPickerContentInterface {
                            override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                                adapter as CustomBSPickerContentAdapter

                                collectLatestLifeCycleFlow(viewModel.ciaList) { newData ->
                                    adapter.updateData(newData)
                                }
                            }

                            override fun onItemClick(position: Int) {
                                setContent(viewModel.ciaList.value[position])
                            }

                            override fun onGetMoreData() {
                                viewModel.getAvailability()
                            }
                        })

                    dialog.show(childFragmentManager, CustomBSPickerContent::class.java.simpleName)
                }
            })
        }

        binding.availability.apply {
            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    if (viewModel.ciaState.value == RequestState.LOADING) return

                    val dialog = CustomBSPickerContent(
                        "Pilih Availability",
                        object : CustomBSPickerContentInterface {
                            override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                                adapter as CustomBSPickerContentAdapter

                                collectLatestLifeCycleFlow(viewModel.ciaList) { newData ->
                                    adapter.updateData(newData)
                                }
                            }

                            override fun onItemClick(position: Int) {
                                setContent(viewModel.ciaList.value[position])
                            }

                            override fun onGetMoreData() {
                                viewModel.getAvailability()
                            }
                        })

                    dialog.show(
                        childFragmentManager,
                        CustomBSPickerContent::class.java.simpleName
                    )
                }
            })

            setTitle("Availability")

            setContent("Pilih Availability")
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