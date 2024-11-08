package simart.umby.android.pages.manajemen_inventaris.data_barang_aset

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import simart.umby.android.R
import simart.umby.android.databinding.ActivityDataBarangAsetBinding
import simart.umby.android.utils.getStatusBarHeight

class DataBarangAsetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBarangAsetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDataBarangAsetBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupView()
    }

    fun setupView() {
        binding.toolbarLayout.setPadding(0, getStatusBarHeight(), 0, 0)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.expandableViewHeader.setOnClickListener {
            binding.expandableViewIcon.setImageResource(if (!binding.expandableView.isExpanded) R.drawable.ic_collapse else R.drawable.ic_expand)
            binding.expandableView.setExpanded(!binding.expandableView.isExpanded)
        }

        binding.expandableDeskripsiHeader.setOnClickListener {
            binding.expandableDeskripsiIcon.setImageResource(if (!binding.expandableDeskripsi.isExpanded) R.drawable.ic_collapse else R.drawable.ic_expand)
            binding.expandableDeskripsi.setExpanded(!binding.expandableDeskripsi.isExpanded)
        }
    }
}