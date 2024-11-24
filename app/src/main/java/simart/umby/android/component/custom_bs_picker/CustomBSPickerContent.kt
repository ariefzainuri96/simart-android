package simart.umby.android.component.custom_bs_picker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import simart.umby.android.databinding.CustomBsPickerContentBinding

interface CustomBSPickerContentInterface {
    fun onRecyclerViewReady(adapter: RecyclerView.Adapter<*>?)
}

class CustomBSPickerContent(
    private val title: String,
    private val customBSPickerContentInterface: CustomBSPickerContentInterface
) : BottomSheetDialogFragment() {
    lateinit var binding: CustomBsPickerContentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CustomBsPickerContentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        binding.title.text = title

        // set adapter
        val adapter = CustomBSPickerContentAdapter()
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager

        customBSPickerContentInterface.onRecyclerViewReady(binding.recyclerView.adapter)
    }
}