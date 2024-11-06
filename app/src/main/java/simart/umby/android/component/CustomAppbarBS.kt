package simart.umby.android.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import simart.umby.android.R
import simart.umby.android.databinding.CustomAppbarBsBinding

@SuppressLint("CustomViewStyleable")
class CustomAppbarBS @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomAppbarBsBinding =
        CustomAppbarBsBinding.inflate(LayoutInflater.from(context), this, true)
//    private lateinit var closeClickListener: OnClickListener

    init {
        // Retrieve custom attributes
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextEdit)
        val title = typedArray.getString(R.styleable.CustomTextEdit_title) ?: "Testing"
        val action1 = typedArray.getDrawable(R.styleable.CustomTextEdit_android_src)
        typedArray.recycle()

        // Set text for TextViews
        binding.title.text = title

        if (action1 != null) {
            binding.icAction1.setImageDrawable(action1)
            binding.icAction1.visibility = VISIBLE
        }
    }

    fun setIcCloseClick(click: OnClickListener) {
//        this.closeClickListener = click

        binding.icClose.setOnClickListener(click)
    }

    fun setIcAction1Click(click: OnClickListener) {
        binding.icAction1.setOnClickListener(click)
    }
}