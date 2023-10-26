package com.wahidabd.synrgy.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wahidabd.synrgy.databinding.FragmentLoginBinding
import com.wahidabd.synrgy.utils.SharedPreferences
import com.wahidabd.synrgy.utils.navigateArgs
import com.wahidabd.synrgy.utils.textTrim
import com.wahidabd.synrgy.utils.toast
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {

    private val pref: SharedPreferences by inject()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnLogin.setOnClickListener { handleLogin() }
            tvNoAccount.setOnClickListener { navigateArgs(LoginFragmentDirections.actionLoginFragmentToRegisterFragment()) }
        }
    }

    private fun handleLogin() = with(binding){
        val email = tilEmail.textTrim()
        val password = tilPassword.textTrim()

        if (email.isEmpty() || password.isEmpty()){
            toast("Please fill all field")
        } else {
            if (pref.login(email, password)){
                pref.setLogin(true)
                toast("Login Success")
                navigateArgs(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}