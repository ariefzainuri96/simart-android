package simart.umby.android.pages.manajemen_inventaris

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import simart.umby.android.databinding.ActivityManajemenInventarisBinding
import simart.umby.android.pages.manajemen_inventaris.data_barang_aset.DataBarangAsetActivity
import simart.umby.android.utils.Utils
import simart.umby.android.utils.getStatusBarHeight

class ManajemenInventarisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManajemenInventarisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityManajemenInventarisBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupView()
    }

    fun setupView() {
        Utils.Companion.setStatusBarShown(this, binding.root)

        binding.toolbarLayout.setPadding(0, getStatusBarHeight(), 0, 0)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.dataBarangAset.setOnClickListener {
            startActivity(Intent(this@ManajemenInventarisActivity, DataBarangAsetActivity::class.java))
        }

        binding.dataBarangHabisPakai.setOnClickListener {}
    }
}