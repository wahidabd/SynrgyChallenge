package com.wahidabd.synrgy.presentation.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wahidabd.synrgy.R
import com.wahidabd.synrgy.databinding.ActivityLoginBinding
import com.wahidabd.synrgy.presentation.main.MainActivity
import com.wahidabd.synrgy.utils.textTrim
import com.wahidabd.synrgy.utils.toast
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    private val viewModel: AuthViewModel by inject()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
        observable()
    }

    private fun handleClick() = with(binding) {
        tvNoAccount.setOnClickListener { RegisterActivity.start(this@LoginActivity) }
        btnLogin.setOnClickListener { handleLogin() }
    }

    private fun handleLogin() = with(binding) {
        val email = tilEmail.textTrim()
        val password = tilPassword.textTrim()

        if (viewModel.validateLogin(email, password)) {
            viewModel.getUser(email, password)
        } else {
            toast(getString(R.string.message_check_your_input))
        }
    }

    private fun observable() {
        viewModel.isLoginSuccess.observe(this) { success ->
            if (success) {
                viewModel.setLogin(true)
                MainActivity.start(this)
                finish()
            } else {
                toast(getString(R.string.message_invalid_email_password))
            }
        }
    }
}