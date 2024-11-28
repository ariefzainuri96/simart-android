package simart.umby.android.pages.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import dagger.hilt.android.AndroidEntryPoint
import simart.umby.android.R
import simart.umby.android.databinding.ActivityLoginBinding
import simart.umby.android.pages.dashboard.DashboardActivity
import simart.umby.android.utils.RequestState
import simart.umby.android.utils.Utils
import simart.umby.android.utils.ValidationType
import simart.umby.android.utils.collectLatestLifeCycleFlow

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        eventHandler()
    }

    private fun eventHandler() {
        collectLatestLifeCycleFlow(viewModel.loginForm) {
            binding.loginButton.isEnabled = it.enableLoginButton()
            binding.loginButton.setBackgroundColor(ContextCompat.getColor(this, if (it.enableLoginButton()) R.color.primary else R.color.grey3))
            binding.checkbox.isChecked = it.checkbox
        }

        collectLatestLifeCycleFlow(viewModel.loginState) {
            if (it == RequestState.SUCCESS) {
                startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                finish()
            }
        }

        binding.usernameInput.getTextInput().doOnTextChanged { text, _, _, _ ->
            viewModel.updateLoginForm { copy(username = text.toString()) }

            binding.usernameInput.setError(
                Utils.Companion.commonInputValidator(
                    text.toString(),
                    ValidationType.EMAIL
                )
            )
        }

        binding.passwordInput.getTextInput().doOnTextChanged { text, _, _, _ ->
            viewModel.updateLoginForm { copy(password = text.toString()) }

            binding.passwordInput.setError(
                Utils.Companion.commonInputValidator(
                    text.toString(),
                    ValidationType.PASSWORD
                )
            )
        }

        binding.loginButton.setOnClickListener {
            viewModel.login()
        }

        binding.checkboxLayout.setOnClickListener {
            viewModel.updateLoginForm { copy(checkbox = !(viewModel.loginForm.value.checkbox)) }
        }
    }

}