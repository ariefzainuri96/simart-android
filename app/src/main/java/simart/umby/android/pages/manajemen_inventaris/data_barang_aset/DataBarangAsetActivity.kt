package simart.umby.android.pages.manajemen_inventaris.data_barang_aset

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import simart.umby.android.adapter.manajemen_inventaris.DataBarangAsetAdapter
import simart.umby.android.adapter.manajemen_inventaris.DataBarangAsetAdapterInterface
import simart.umby.android.databinding.ActivityDataBarangAsetBinding
import simart.umby.android.model.manajemen_inventaris.DataBarangAsetModel
import simart.umby.android.pages.manajemen_inventaris.edit_data_barang_aset.EditDataBarangAsetBS
import simart.umby.android.utils.RequestState
import simart.umby.android.utils.Utils
import simart.umby.android.utils.collectLatestLifeCycleFlow
import simart.umby.android.utils.getStatusBarHeight

@AndroidEntryPoint
class DataBarangAsetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBarangAsetBinding
    private val viewModel: DataBarangAsetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDataBarangAsetBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupView()

        observeData()
    }

    private fun setupView() {
        Utils.setStatusBarShown(this, binding.root)

        binding.toolbarLayout.setPadding(0, getStatusBarHeight(), 0, 0)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.errorLayout.apply {
            setHandleClickListener { viewModel.getDataBarangAset() }
        }
    }

    private fun setupDataBarangAsetAdapter() {
        val adapter = DataBarangAsetAdapter(this, viewModel.listDataBarang.value)

        adapter.setInterface(object : DataBarangAsetAdapterInterface {
            override fun onEditClick(
                position: Int,
                model: DataBarangAsetModel
            ) {
                val bottomSheet = EditDataBarangAsetBS(model)

                bottomSheet.show(supportFragmentManager, EditDataBarangAsetBS::class.java.simpleName)
            }

            override fun onDeleteClick(
                position: Int,
                model: DataBarangAsetModel
            ) {
                println("onDeleteClick")
            }
        })

        binding.dataBarangAsetRV.adapter = adapter

        binding.dataBarangAsetRV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun observeData() {
        collectLatestLifeCycleFlow(viewModel.state) {
            binding.loadingLayout.visibility = if (it == RequestState.LOADING) View.VISIBLE else View.GONE
            binding.errorLayout.visibility = if (it == RequestState.ERROR) View.VISIBLE else View.GONE

            if (it == RequestState.SUCCESS) {
                setupDataBarangAsetAdapter()
            }
        }
    }
}