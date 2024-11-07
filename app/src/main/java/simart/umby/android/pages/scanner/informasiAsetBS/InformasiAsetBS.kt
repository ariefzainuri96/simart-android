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

        binding.appbar.setIcCloseClick {
            dismiss()
        }

        binding.noPO.setContent("PO/2024/123")
        binding.noGRInvoice.setContent("INV-GR-FTI-2024123")
        binding.noSPK.setContent("12345")
        binding.vendor.setContent("Nama Vendor")
        binding.tipeAset.setContent("ATK")
        binding.noInventaris.setContent("12345")
        binding.noBBK.setContent("00000000")
        binding.namaAset.setContent("Epson Stylish")
        binding.deskripsiAset.setContent("-")
        binding.spesifikasiAset.setContent("Projection Technology: Projection Technology: RGB liquid crystal shutter projection system (3LCD)\n" +
                "Brightness1: White Light Output (Normal/Eco): 3,600 lmColour Light Output: 3,600 lm.\n" +
                "Connectivity: USB Interface: USB Type A: 1 (For Wireless LAN, Firmware Update, Copy OSD Settings)")
        binding.kategoriAset.setContent("-")
        binding.subKategoriAset.setContent("-")
        binding.kampus.setContent("-")
        binding.ruang.setContent("-")
        binding.convidentality.setContent("-")
        binding.integrity.setContent("-")
        binding.availability.setContent("-")
        binding.tanggalAkuisisi.setContent("-")
        binding.tanggalDepresiasi.setContent("-")
        binding.sumberAset.setContent("-")
        binding.jumlahAset.setContent("-")
        binding.nvb.setContent("-")
        binding.statusAset.setContent("-")
    }
}