package simart.umby.android.pages.manajemen_aset.pemindahan_aset

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import simart.umby.android.adapter.manajemen_aset.PemindahanAsetAdapter
import simart.umby.android.databinding.ActivityPemindahanAsetBinding
import simart.umby.android.utils.RequestState
import simart.umby.android.utils.Utils
import simart.umby.android.utils.collectLatestLifeCycleFlow
import simart.umby.android.utils.getStatusBarHeight

@AndroidEntryPoint
class PemindahanAsetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPemindahanAsetBinding
    private val viewModel: PemindahanAsetVM by viewModels()

    private val pemindahanAsetAdapter = PemindahanAsetAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPemindahanAsetBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupView()

        observeData()
    }

    private fun setupView() {
        Utils.Companion.setStatusBarShown(this, binding.root)

        binding.toolbarLayout.setPadding(0, getStatusBarHeight(), 0, 0)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.recyclerPemindahanAset.adapter = pemindahanAsetAdapter
        binding.recyclerPemindahanAset.layoutManager = LinearLayoutManager(this, RecyclerView
            .VERTICAL, false)
    }

    private fun observeData() {
        collectLatestLifeCycleFlow(viewModel.pemindahanAset) {
            pemindahanAsetAdapter.updateData(it)
        }

        collectLatestLifeCycleFlow(viewModel.pemindahanAsetState) {
            binding.pemindahanAsetProgress.visibility = if (it == RequestState.LOADING) View.VISIBLE else View
                .GONE

            binding.pemindahanAsetError.visibility = if (it == RequestState.ERROR) View.VISIBLE
            else View.GONE
        }
    }
}