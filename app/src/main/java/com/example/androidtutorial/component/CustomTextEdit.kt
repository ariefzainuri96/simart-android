package com.example.androidtutorial.component

import android.content.Context
import android.content.res.ColorStateList
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.androidtutorial.R
import com.example.androidtutorial.databinding.CustomTextEditBinding
import com.google.android.material.textfield.TextInputEditText

/**
 * TODO: document your custom view class.
 */

class CustomTextEdit @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomTextEditBinding =
        CustomTextEditBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        // Retrieve custom attributes
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextEdit)
        val title = typedArray.getString(R.styleable.CustomTextEdit_title)
        val hint = typedArray.getString(R.styleable.CustomTextEdit_hint)
        val action = typedArray.getInt(R.styleable.CustomTextEdit_android_imeOptions, EditorInfo.IME_ACTION_DONE)
        val borderColor = typedArray.getInt(R.styleable.CustomTextEdit_borderColor, ContextCompat.getColor(context, R.color.line))
        val inputType = typedArray.getInt(R.styleable.CustomTextEdit_android_inputType, InputType.TYPE_CLASS_TEXT)
        val maxLines = typedArray.getInt(R.styleable.CustomTextEdit_android_maxLines, 0)
        val obscure = typedArray.getBoolean(R.styleable.CustomTextEdit_obscure, false)
        typedArray.recycle()

        // set layout
        setBoxStrokeColorSelector(borderColor, binding)

        // Set text for TextViews
        binding.title.text = title
        binding.textInput.hint = hint
        binding.textInput.imeOptions = action
        binding.textInput.maxLines = maxLines
        binding.textInput.inputType = inputType

        if (obscure) {
            binding.textInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.textInput.maxLines = 1
        }
    }

    private fun setBoxStrokeColorSelector(borderColor: Int?, binding: CustomTextEditBinding) {
        //Color from hex string
        val defaultColor = ContextCompat.getColor(context, R.color.line)

        val states = arrayOf(
            intArrayOf(android.R.attr.state_focused),  // focused
            intArrayOf(android.R.attr.state_hovered), // hovered
            intArrayOf(android.R.attr.state_enabled), // enabled
            intArrayOf() // default
        )

        val colors = intArrayOf(borderColor ?: defaultColor, // focused color
            borderColor ?: defaultColor, // hovered color
            borderColor ?: defaultColor, // enabled color
            borderColor ?: defaultColor) // default color

        val myColorList = ColorStateList(states, colors)

        binding.inputLayout.setBoxStrokeColorStateList(myColorList)
    }

    fun getTextInput(): TextInputEditText {
        return binding.textInput
    }

    fun setError(message: String?) {
        binding.inputLayout.isErrorEnabled = message != null
        binding.inputLayout.error = message
    }
}