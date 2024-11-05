package simart.umby.android.pages.scanner.informasiasetbs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import simart.umby.android.databinding.InformasiAsetBsBinding
import simart.umby.android.utils.collectLatestLifeCycleFlow

class InformasiAsetBS: BottomSheetDialogFragment() {

    private val viewModel: InformasiAsetViewModel by viewModels()
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

                sheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                sheet.requestLayout()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectLatestLifeCycleFlow(viewModel.counter) {
            binding.counter.text = "$it"
        }

        binding.btnAdd.setOnClickListener {
            viewModel.addCounter()
        }
    }
}