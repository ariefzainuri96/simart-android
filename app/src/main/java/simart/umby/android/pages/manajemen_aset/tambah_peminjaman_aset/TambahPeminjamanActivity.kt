package simart.umby.android.pages.manajemen_aset.tambah_peminjaman_aset

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import simart.umby.android.adapter.manajemen_aset.tambah_peminjaman_aset.DetailPeminjamanAdapter
import simart.umby.android.component.reusable.CustomDatePickerInterface
import simart.umby.android.component.reusable.custom_bs_picker.CustomBSPickerContent
import simart.umby.android.component.reusable.custom_bs_picker.CustomBSPickerContentAdapter
import simart.umby.android.component.reusable.custom_bs_picker.CustomBSPickerContentInterface
import simart.umby.android.component.reusable.custom_bs_picker.CustomBottomSheetPickerInterface
import simart.umby.android.databinding.ActivityTambahPeminjamanBinding
import simart.umby.android.model.manajemen_aset.DetailPeminjamanAsetForm
import simart.umby.android.model.manajemen_aset.PeminjamanAsetForm
import simart.umby.android.utils.Utils
import simart.umby.android.utils.collectLatestLifeCycleFlow
import simart.umby.android.utils.getStatusBarHeight

@AndroidEntryPoint
class TambahPeminjamanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahPeminjamanBinding
    private val viewModel: TambahPeminjamanVM by viewModels()
    private val detailPeminjamanAdapter = DetailPeminjamanAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTambahPeminjamanBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupView()

        observeData()
    }

    private fun observeData() {
        collectLatestLifeCycleFlow(viewModel.detailPeminjamanFormError) {
            binding.namaAset.setError(it.firstOrNull { error -> error.key == "namaAset" }?.error)
            binding.jumlah.setError(it.firstOrNull { error -> error.key == "jumlah" }?.error)
            binding.satuan.setError(it.firstOrNull { error -> error.key == "satuan" }?.error)
        }

        collectLatestLifeCycleFlow(viewModel.formError) {
            binding.noPeminjaman.setError(it.firstOrNull { error -> error.key == "noPeminjam" }?.error)
            binding.tipePeminjam.setError(it.firstOrNull { error -> error.key == "tipePeminjam" }?.error)
            binding.identitasPeminjam.setError(it.firstOrNull { error -> error.key == "identitasPeminjam" }?.error)
            binding.namaPeminjam.setError(it.firstOrNull { error -> error.key == "namaPeminjam" }?.error)
            binding.alamatPeminjam.setError(it.firstOrNull { error -> error.key == "alamatPeminjam" }?.error)
            binding.fakultas.setError(it.firstOrNull { error -> error.key == "fakultas" }?.error)
            binding.noTelp.setError(it.firstOrNull { error -> error.key == "noTelepon" }?.error)
            binding.tanggalPinjam.setError(it.firstOrNull { error -> error.key == "tanggalPinjam" }?.error)
            binding.tanggalKembali.setError(it.firstOrNull { error -> error.key == "tanggalKembali" }?.error)
        }
    }

    private fun setupView() {
        Utils.Companion.setStatusBarShown(this, binding.root)

        binding.toolbarLayout.setPadding(0, getStatusBarHeight(), 0, 0)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.detailPeminjamanRV.adapter = detailPeminjamanAdapter
        binding.detailPeminjamanRV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.noPeminjaman.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(noPeminjam = text.toString()) }
            }
        }

        binding.btnSimpan.setOnClickListener {
            if (viewModel.checkFormValid()) {
                viewModel.updateForm { PeminjamanAsetForm() }
            }
        }

        binding.btnTambahkan.setOnClickListener {
            if (viewModel.checkDetailPeminjamanFormValid()) {
                detailPeminjamanAdapter.updateData(viewModel.detailPeminjamanForm.value)
                viewModel.updateDetailPeminjamanForm { DetailPeminjamanAsetForm() }
            }
        }

        binding.tipePeminjam.apply {
            setTitle("Tipe Peminjam")
            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    val dialog = CustomBSPickerContent(title = "Pilih Tipe Peminjam", object :
                        CustomBSPickerContentInterface {
                        override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                            adapter as CustomBSPickerContentAdapter

                            collectLatestLifeCycleFlow(viewModel.tipePeminjamList) { newData ->
                                adapter.updateData(newData)
                            }
                        }

                        override fun onItemClick(position: Int) {
                            viewModel.updateForm { copy(tipePeminjam = viewModel.tipePeminjamList.value[position]) }
                            setContent(viewModel.tipePeminjamList.value[position])
                        }

                        override fun onGetMoreData() {}
                    })

                    dialog.show(
                        supportFragmentManager,
                        CustomBSPickerContent::class.java.simpleName
                    )
                }
            })
        }

        binding.identitasPeminjam.apply {
            setTitle("Identitas Peminjam")
            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    val dialog = CustomBSPickerContent(title = "Pilih Identitas Peminjam", object :
                        CustomBSPickerContentInterface {
                        override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                            adapter as CustomBSPickerContentAdapter

                            collectLatestLifeCycleFlow(viewModel.identitasPeminjamList) { newData ->
                                adapter.updateData(newData)
                            }
                        }

                        override fun onItemClick(position: Int) {
                            viewModel.updateForm { copy(identitasPeminjam = viewModel.identitasPeminjamList.value[position]) }
                            setContent(viewModel.identitasPeminjamList.value[position])
                        }

                        override fun onGetMoreData() {}
                    })

                    dialog.show(
                        supportFragmentManager,
                        CustomBSPickerContent::class.java.simpleName
                    )
                }
            })
        }

        binding.namaPeminjam.getTextInput().doOnTextChanged { text, _, _, _ ->
            viewModel.updateForm { copy(namaPeminjam = text.toString()) }
        }

        binding.alamatPeminjam.apply {
            setTextLayout(minLine = 4, maxLine = 4)

            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(alamatPeminjam = text.toString()) }
            }
        }

        binding.fakultas.apply {
            setTitle("Fakultas")
            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    val dialog = CustomBSPickerContent(title = "Pilih Fakultas", object :
                        CustomBSPickerContentInterface {
                        override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                            adapter as CustomBSPickerContentAdapter

                            collectLatestLifeCycleFlow(viewModel.fakultasList) { newData ->
                                adapter.updateData(newData)
                            }
                        }

                        override fun onItemClick(position: Int) {
                            viewModel.updateForm { copy(fakultas = viewModel.fakultasList.value[position]) }
                            setContent(viewModel.fakultasList.value[position])
                        }

                        override fun onGetMoreData() {}
                    })

                    dialog.show(
                        supportFragmentManager,
                        CustomBSPickerContent::class.java.simpleName
                    )
                }
            })
        }

        binding.noTelp.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm { copy(noTelepon = text.toString()) }
            }
        }

        binding.tanggalPinjam.apply {
            setTitle("Tanggal Pinjam")
            handleAction(object : CustomDatePickerInterface {
                override fun onDateSelected(value: String) {
                    viewModel.updateForm { copy(tanggalPinjam = value) }
                }
            })
        }

        binding.tanggalKembali.apply {
            setTitle("Tanggal Kembali")
            handleAction(object : CustomDatePickerInterface {
                override fun onDateSelected(value: String) {
                    viewModel.updateForm { copy(tanggalKembali = value) }
                }
            })
        }

        binding.satuan.apply {
            collectLatestLifeCycleFlow(viewModel.detailPeminjamanForm) {
                if (it.satuan.isEmpty()) {
                    setDefaultContent("Pilih Satuan")
                } else {
                    setContent(it.satuan)
                }
            }

            setTitle("Satuan")
            setAction(object : CustomBottomSheetPickerInterface {
                override fun showBottomSheet() {
                    val dialog = CustomBSPickerContent(title = "Pilih Satuan", object :
                        CustomBSPickerContentInterface {
                        override fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?) {
                            adapter as CustomBSPickerContentAdapter

                            collectLatestLifeCycleFlow(viewModel.satuanList) { newData ->
                                adapter.updateData(newData)
                            }
                        }

                        override fun onItemClick(position: Int) {
                            viewModel.updateDetailPeminjamanForm { copy(satuan = viewModel.satuanList.value[position]) }
                        }

                        override fun onGetMoreData() {}
                    })

                    dialog.show(
                        supportFragmentManager,
                        CustomBSPickerContent::class.java.simpleName
                    )
                }
            })
        }

        binding.namaAset.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateDetailPeminjamanForm { copy(namaAset = text.toString()) }
            }
        }

        binding.jumlah.apply {
            getTextInput().doOnTextChanged { text, _, _, _ ->
                viewModel.updateDetailPeminjamanForm { copy(jumlah = text.toString()) }
            }
        }
    }
}