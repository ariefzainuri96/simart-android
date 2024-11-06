package simart.umby.android.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import simart.umby.android.R
import simart.umby.android.databinding.CustomTitleContentBinding

@SuppressLint("CustomViewStyleable")
class CustomTitleContent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomTitleContentBinding =
        CustomTitleContentBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        // Retrieve custom attributes
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextEdit)
        val title = typedArray.getString(R.styleable.CustomTextEdit_title) ?: "Testing"
        typedArray.recycle()

        // Set text for TextViews
        binding.title.text = title
    }

    fun setContent(content: String) {
        binding.content.text = content
    }
}