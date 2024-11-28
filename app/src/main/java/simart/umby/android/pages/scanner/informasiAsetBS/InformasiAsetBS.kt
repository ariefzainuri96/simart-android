package simart.umby.android.pages.scanner.informasiAsetBS

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import simart.umby.android.databinding.InformasiAsetBsBinding
import simart.umby.android.utils.collectLatestLifeCycleFlow

class InformasiAsetBS(
    private val rawValue: String
): BottomSheetDialogFragment() {

//    private val viewModel: InformasiAsetViewModel by viewModels()
    private lateinit var binding: InformasiAsetBsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InformasiAsetBsBinding.inflate(inflater)

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

        println("rawValue => $rawValue")

//        collectLatestLifeCycleFlow(viewModel.counter) {
//            binding.counter.text = "$it"
//        }

        binding.appbar.apply {
            setIcCloseClick {
                dismiss()
            }

            setLayout("Informasi Aset")
        }

        binding.noPO.setContent("No. PO", "PO/2024/123")
        binding.noGRInvoice.setContent("No. Invoice", "INV-GR-FTI-2024123")
        binding.noSPK.setContent("No. SPK", "12345")
        binding.vendor.setContent("Vendor", "Nama Vendor")
        binding.tipeAset.setContent("Tipe Aset", "ATK")
        binding.noInventaris.setContent("No. Inventaris", "12345")
        binding.noBBK.setContent("No. BBK", "00000000")
        binding.namaAset.setContent("Nama Aset", "Epson Stylish")
        binding.deskripsiAset.setContent("Deskripsi Aset", "-")
        binding.spesifikasiAset.setContent("Spesifikasi Aset", "Projection Technology: Projection" +
                " Technology: RGB liquid crystal shutter projection system (3LCD)\n" +
                "Brightness1: White Light Output (Normal/Eco): 3,600 lmColour Light Output: 3,600 lm.\n" +
                "Connectivity: USB Interface: USB Type A: 1 (For Wireless LAN, Firmware Update, Copy OSD Settings)")
        binding.kategoriAset.setContent("Kategori Aset", "-")
        binding.subKategoriAset.setContent("Sub Kategori Aset", "-")
        binding.kampus.setContent("Kampus", "-")
        binding.ruang.setContent("Ruang", "-")
        binding.convidentality.setContent("Convidentality", "-")
        binding.integrity.setContent("Integrity", "-")
        binding.availability.setContent("Availability", "-")
        binding.tanggalAkuisisi.setContent("Tanggal Akuisisi", "-")
        binding.tanggalDepresiasi.setContent("Tanggal Depresiasi", "-")
        binding.sumberAset.setContent("Sumber Aset", "-")
        binding.jumlahAset.setContent("Jumlah Aset", "-")
        binding.nvb.setContent("NVB", "-")
        binding.statusAset.setContent("Status Aset", "-")
    }
}