package simart.umby.android.component

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout

class ExpandableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var contentView: View? = null
    var isExpanded = false
    private var expandedHeight: Int = 0
    private val animationDuration = 300L // Duration of the expand/collapse animation

    init {
        orientation = VERTICAL
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        // Assume the first child view is the expandable content
        if (childCount > 0) {
            // the child should contain ViewGroup like LinearLayout, ConstraintLayout, RelativeLayout
            // if want to show more than 1 child
            contentView = getChildAt(0)
            contentView?.visibility = GONE

//            contentView?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//                override fun onGlobalLayout() {
//                    contentView?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
//                    expandedHeight = contentView?.measuredHeight ?: 0
//                }
//            })
        }
    }

    private fun measureExpandedHeight() {
        if (expandedHeight > 0) return

        contentView?.measure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        )
        expandedHeight = contentView?.measuredHeight ?: 0
    }

    fun setExpanded(expand: Boolean, animate: Boolean = true) {
        if (isExpanded == expand) return
        isExpanded = expand

        // Measure the expanded height dynamically
        measureExpandedHeight()

        if (animate) {
            animateExpansion()
        } else {
            contentView?.visibility = if (expand) VISIBLE else GONE
            contentView?.layoutParams?.height = if (expand) expandedHeight else 0
            contentView?.requestLayout()
        }
    }

    private fun animateExpansion() {
        val startHeight = if (isExpanded) 0 else expandedHeight
        val endHeight = if (isExpanded) expandedHeight else 0

        val animator = ValueAnimator.ofInt(startHeight, endHeight).apply {
            duration = animationDuration
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { valueAnimator ->
                val height = valueAnimator.animatedValue as Int
                contentView?.layoutParams?.height = height
                contentView?.requestLayout()
                contentView?.visibility = if (height > 0) VISIBLE else GONE
            }
        }
        animator.start()
    }
}