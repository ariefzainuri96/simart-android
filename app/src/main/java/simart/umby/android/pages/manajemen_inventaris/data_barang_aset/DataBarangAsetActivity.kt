package simart.umby.android.pages.manajemen_inventaris.data_barang_aset

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.databinding.ActivityDataBarangAsetBinding
import simart.umby.android.pages.manajemen_inventaris.data_barang_aset.model.DataBarangAsetModel
import simart.umby.android.utils.RequestState
import simart.umby.android.utils.Utils
import simart.umby.android.utils.collectLatestLifeCycleFlow
import simart.umby.android.utils.getStatusBarHeight

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
    }

    private fun setupDataBarangAsetAdapter() {
        val adapter = DataBarangAsetAdapter(this, viewModel.listDataBarang.value)

        adapter.setOnClickListener(object : DataBarangAsetAdapter.OnClickListener {
            override fun onClick(
                position: Int,
                model: DataBarangAsetModel
            ) {
                println("TEST")
            }
        })

        binding.dataBarangAsetRV.adapter = adapter

        binding.dataBarangAsetRV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun observeData() {
        collectLatestLifeCycleFlow(viewModel.state) {
            binding.loadingLayout.visibility = if (it == RequestState.LOADING) View.VISIBLE else View.GONE

            if (it == RequestState.SUCCESS) {
                setupDataBarangAsetAdapter()
            }
        }
    }
}