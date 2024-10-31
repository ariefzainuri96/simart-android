package com.example.androidtutorial.pages.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.androidtutorial.R
import com.example.androidtutorial.databinding.ActivityLoginBinding
import com.example.androidtutorial.pages.dashboard.DashboardActivity
import com.example.androidtutorial.utils.InputType
import com.example.androidtutorial.utils.Utils

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        eventHandler()
    }

    private fun eventHandler() {
        viewModel.loginForm.observe(this) {
            binding.loginButton.isEnabled = it.enableLoginButton()
            binding.loginButton.setBackgroundColor(ContextCompat.getColor(this, if (it.enableLoginButton()) R.color.primary else R.color.grey3))
            binding.checkbox.isChecked = it.checkbox
        }

        binding.usernameInput.getTextInput().doOnTextChanged { text, _, _, _ ->
            viewModel.loginForm.value?.username = text.toString()
            viewModel.updateLoginForm(viewModel.loginForm)

            binding.usernameInput.setError(Utils.commonInputValidator(text.toString(), InputType.EMAIL))
        }

        binding.passwordInput.getTextInput().doOnTextChanged { text, _, _, _ ->
            viewModel.loginForm.value?.password = text.toString()
            viewModel.updateLoginForm(viewModel.loginForm)
            binding.passwordInput.setError(Utils.commonInputValidator(text.toString(), InputType.PASSWORD))
        }

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        binding.checkboxLayout.setOnClickListener {
            viewModel.loginForm.value?.checkbox = !(viewModel.loginForm.value?.checkbox ?: false)
            viewModel.updateLoginForm(viewModel.loginForm)
        }
    }

}