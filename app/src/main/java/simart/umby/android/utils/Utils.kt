package simart.umby.android.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.FragmentActivity
import simart.umby.android.R

enum class InputType {
    EMAIL, PASSWORD
}

class Utils {
    companion object {
        val FontName = FontFamily(
            Font(R.font.sf_pro_100, FontWeight.Thin),
            Font(R.font.sf_pro_200,FontWeight.ExtraLight),
            Font(R.font.sf_pro_300, FontWeight.Light),
            Font(R.font.sf_pro_400, FontWeight.Normal),
            Font(R.font.sf_pro_500, FontWeight.Medium),
            Font(R.font.sf_pro_600, FontWeight.SemiBold),
            Font(R.font.sf_pro_700, FontWeight.Bold),
            Font(R.font.sf_pro_900, FontWeight.Black),
        )

        fun commonInputValidator(message: String, inputType: InputType): String? {
            return when (inputType) {
                InputType.EMAIL -> {
                    if (message.isEmpty()) "Input can't be empty"
                    else null
                }
                InputType.PASSWORD -> {
                    if (message.isEmpty()) "Password can't be empty"
                    else null
                }
            }
        }

        fun setStatusBarTransparent(activity: Activity, view: View) {
            activity.apply {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(this, R.color.defaultStatusBar)
                WindowCompat.setDecorFitsSystemWindows(window, false)
                ViewCompat.setOnApplyWindowInsetsListener(view) { root, windowInset ->
                    val inset = windowInset.getInsets(WindowInsetsCompat.Type.systemBars())
                    root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        leftMargin = inset.left
                        bottomMargin = inset.bottom
                        rightMargin = inset.right
                        topMargin = 0
                    }
                    WindowInsetsCompat.CONSUMED
                }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun setStatusBarShown(activity: Activity, view: View, top: Int? = 0) {
            activity.apply {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(this, R.color.defaultStatusBar)
                WindowCompat.setDecorFitsSystemWindows(window, false)

                // For older Android versions, this is not directly supported
                // You may need to set a dark status bar background to make icons appear white
                WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

                ViewCompat.setOnApplyWindowInsetsListener(view) { root, windowInset ->
                    val inset = windowInset.getInsets(WindowInsetsCompat.Type.systemBars())
                    val inset1 = windowInset.getInsets(WindowInsetsCompat.Type.statusBars())
                    root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        leftMargin = inset.left
                        bottomMargin = inset.bottom
                        topMargin = top ?: inset1.top
                        rightMargin = inset.right
                    }
                    WindowInsetsCompat.CONSUMED
                }
            }
        }

        fun getWindowHeight(activity: FragmentActivity?): Int {
            val displayMetrics = DisplayMetrics()

            activity?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    @Suppress("DEPRECATION")
                    it.display?.getRealMetrics(displayMetrics)
                } else {
                    @Suppress("DEPRECATION")
                    it.windowManager.defaultDisplay.getMetrics(displayMetrics)
                }
            }

            return displayMetrics.heightPixels
        }
    }
}