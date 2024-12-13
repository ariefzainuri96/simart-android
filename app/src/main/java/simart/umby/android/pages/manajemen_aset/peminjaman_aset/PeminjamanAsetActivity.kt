package simart.umby.android.pages.manajemen_aset.peminjaman_aset

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import simart.umby.android.adapter.manajemen_aset.PeminjamanAsetAdapter
import simart.umby.android.databinding.ActivityPeminjamanAsetBinding
import simart.umby.android.pages.manajemen_aset.tambah_peminjaman_aset.TambahPeminjamanActivity
import simart.umby.android.utils.RequestState
import simart.umby.android.utils.Utils
import simart.umby.android.utils.collectLatestLifeCycleFlow
import simart.umby.android.utils.getStatusBarHeight

class PeminjamanAsetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPeminjamanAsetBinding
    private val viewModel: PeminjamanAsetViewModel by viewModels()
    private val peminjamanAsetAdapter = PeminjamanAsetAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPeminjamanAsetBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupView()

        observeData()
    }

    private fun observeData() {
        collectLatestLifeCycleFlow(viewModel.peminjamanAsetList) {
            peminjamanAsetAdapter.updateData(it)
        }

        collectLatestLifeCycleFlow(viewModel.peminjamanAsetState) {
            binding.peminjamanAsetLoading.visibility = if (it == RequestState.LOADING) View.VISIBLE else View.GONE
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

        binding.rvPeminjamanAset.adapter = peminjamanAsetAdapter
        binding.rvPeminjamanAset.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,
            false)

        binding.btnAddPeminjamanAset.setOnClickListener {
            startActivity(Intent(this@PeminjamanAsetActivity, TambahPeminjamanActivity::class.java))
        }
    }
}