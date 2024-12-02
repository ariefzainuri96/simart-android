package simart.umby.android.pages.ManajamenAset

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import simart.umby.android.component.CommonMenuInterface
import simart.umby.android.databinding.ActivityManajemenAsetBinding
import simart.umby.android.pages.ManajamenAset.Pages.PeminjamanAset.PeminjamanAsetActivity
import simart.umby.android.utils.Utils
import simart.umby.android.utils.getStatusBarHeight

class ManajemenAsetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManajemenAsetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityManajemenAsetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        Utils.Companion.setStatusBarShown(this, binding.root)

        binding.toolbarLayout.setPadding(0, getStatusBarHeight(), 0, 0)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.peminjamanAset.apply {
            setTitle("Peminjaman Aset")

            setInterface(object : CommonMenuInterface {
                override fun handleOnClick() {
                    startActivity(Intent(this@ManajemenAsetActivity, PeminjamanAsetActivity::class.java))
                }
            })
        }

        binding.tracingAset.apply {
            setTitle("Tracing Aset")

            setInterface(object : CommonMenuInterface {
                override fun handleOnClick() {
                    println("Tracing Aset")
                }
            })
        }

        binding.pemindahanAset.apply {
            setTitle("Pemindahan Aset")

            setInterface(object : CommonMenuInterface {
                override fun handleOnClick() {
                    println("Pemindahan Aset")
                }
            })
        }

        binding.tracingAset.apply {
            setTitle("Pemeliharaan Aset")

            setInterface(object : CommonMenuInterface {
                override fun handleOnClick() {
                    println("Pemeliharaan Aset")
                }
            })
        }

    }
}