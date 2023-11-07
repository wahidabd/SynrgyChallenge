package com.wahidabd.synrgy.presentation.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wahidabd.synrgy.R
import com.wahidabd.synrgy.databinding.ActivityRegisterBinding
import com.wahidabd.synrgy.domain.auth.AuthUser
import com.wahidabd.synrgy.utils.onBackPress
import com.wahidabd.synrgy.utils.textTrim
import com.wahidabd.synrgy.utils.toast
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    private val viewModel: AuthViewModel by inject()
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
    }

    private fun handleClick() = with(binding) {
        tvHaveAccount.setOnClickListener { onBackPress() }
        btnRegister.setOnClickListener { handleRegister() }
    }

    private fun handleRegister() = with(binding) {
        val name = tilName.textTrim()
        val email = tilEmail.textTrim()
        val password = tilPassword.textTrim()

        if (viewModel.validateRegister(name, email, password)) {
            viewModel.setUser(AuthUser(name, email, password))
            toast(getString(R.string.message_success_register))
            onBackPress()
        } else {
            toast(getString(R.string.message_check_your_input))
        }
    }
}