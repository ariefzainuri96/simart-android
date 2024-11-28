package simart.umby.android.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import simart.umby.android.databinding.CustomTitleContentBinding

class CustomTitleContent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var isContentSet = false
    private var binding: CustomTitleContentBinding =
        CustomTitleContentBinding.inflate(LayoutInflater.from(context), this, true)

    fun setContent(title: String, content: String) {
        binding.title.text = title
        binding.content.text = content
        isContentSet = true
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (!isContentSet) {
            throw IllegalStateException("setContent() must be called on CustomTitleContent before it is used.")
        }
    }
}