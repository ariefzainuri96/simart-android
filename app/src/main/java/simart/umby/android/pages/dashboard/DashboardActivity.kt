package simart.umby.android.pages.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import simart.umby.android.databinding.ActivityDashboardBinding
import simart.umby.android.pages.ManajamenAset.ManajemenAsetActivity
import simart.umby.android.pages.dashboard.adapter.MenuAdapter
import simart.umby.android.pages.dashboard.adapter.NewsViewPagerAdapter
import simart.umby.android.pages.dashboard.model.MenuModel
import simart.umby.android.pages.manajemen_inventaris.ManajemenInventarisActivity
import simart.umby.android.pages.scanner.ScannerActivity
import simart.umby.android.pages.scanner.informasiAsetBS.InformasiAsetBS
import simart.umby.android.pages.task_approval.TaskApprovalActivity
import simart.umby.android.utils.RequestState
import simart.umby.android.utils.Utils
import simart.umby.android.utils.cameraPermissionRequest
import simart.umby.android.utils.collectLatestLifeCycleFlow
import simart.umby.android.utils.dpToPx
import simart.umby.android.utils.isPermissionGranted
import simart.umby.android.utils.openPermissionSetting

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()

    private val cameraPermission = android.Manifest.permission.CAMERA

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            startScanner()
        }
    }

    private fun startScanner() {
        ScannerActivity.startScanner(this) { barcodes ->
            if (barcodes.isNotEmpty() && barcodes.first().rawValue != null) {
                println("scannedQrCode => ${barcodes.first().rawValue}")

                lifecycleScope.launch {
                    // add delay to wait until scanner activity is closed
                    delay(500L)

                    println("goToInformasiAset")

                    val dialog = InformasiAsetBS(barcodes.first().rawValue!!)

                    dialog.show(supportFragmentManager, InformasiAsetBS::class.java.simpleName)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)

        setContentView(binding.root)

        observeData()

        setupView()

        setMenuRecyclerView()
    }

    private fun requestCameraAndStartScanner() {
        if (isPermissionGranted(cameraPermission)) {
            startScanner()
        } else {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        when {
            shouldShowRequestPermissionRationale(cameraPermission) -> {
                cameraPermissionRequest {
                    openPermissionSetting()
                }
            }
            else -> {
                requestPermissionLauncher.launch(cameraPermission)
            }
        }
    }

    private fun setupView() {
        Utils.Companion.setStatusBarTransparent(this, binding.root)

        binding.scanQRLayout.setOnClickListener {
            requestCameraAndStartScanner()
        }
    }

    private fun observeData() {
        collectLatestLifeCycleFlow(viewModel.newsState) { state ->
            binding.newsLoading.visibility = if (state == RequestState.LOADING) View.VISIBLE else View.GONE
            binding.errorLayout.visibility = if (state == RequestState.ERROR) View.VISIBLE else View.GONE

            if (state == RequestState.SUCCESS) {
                setupNewsViewPager()
            }
        }
    }

    private fun setMenuRecyclerView() {
        val adapter = MenuAdapter(viewModel.menus)
        adapter.setOnClickListener(object : MenuAdapter.OnClickListener {
            override fun onClick(
                position: Int,
                model: MenuModel
            ) {
                when (position) {
                    0 -> startActivity(Intent(this@DashboardActivity, ManajemenInventarisActivity::class.java))
                    1 -> println("Manajemen Barang Pakai Habis")
                    2 -> startActivity(Intent(this@DashboardActivity, ManajemenAsetActivity::class.java))
                    3-> startActivity(Intent(this@DashboardActivity, TaskApprovalActivity::class.java))
                }   
            }
        })

        binding.menuRecyclerView.adapter = adapter

        binding.menuRecyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    private fun setupNewsViewPager() {
        binding.newsViewPager.adapter = NewsViewPagerAdapter(viewModel.news)

        //set the orientation of the viewpager using ViewPager2.orientation
        binding.newsViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // registering for page change callback
        binding.newsViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    // use position to get the page
                    println("newsPageChanged $position")
                }
            }
        )

        binding.newsViewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 2

            //increase this offset to show more of left/right
            val offsetPx = 16.dpToPx(resources.displayMetrics)
            setPadding(offsetPx, 0, offsetPx, 0)

            //increase this offset to increase distance between 2 items
            val pageMarginPx = 8.dpToPx(resources.displayMetrics)
            setPageTransformer(MarginPageTransformer(pageMarginPx))
        }

        binding.newsIndicator.setViewPager(binding.newsViewPager)
    }
}