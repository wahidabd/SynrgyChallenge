package com.wahidabd.synrgy.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wahidabd.synrgy.databinding.FragmentRegisterBinding
import com.wahidabd.synrgy.domain.auth.AuthUser
import com.wahidabd.synrgy.utils.SharedPreferences
import com.wahidabd.synrgy.utils.navigateUp
import com.wahidabd.synrgy.utils.textTrim
import com.wahidabd.synrgy.utils.toast
import org.koin.android.ext.android.inject


class RegisterFragment : Fragment() {

    private val pref: SharedPreferences by inject()

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener { handleRegister() }
        binding.tvHaveAccount.setOnClickListener { navigateUp() }
    }

    private fun handleRegister() = with(binding){
        val name = tilName.textTrim()
        val email = tilEmail.textTrim()
        val password = tilPassword.textTrim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            toast("Please fill all field")
        } else {
            val authUser = AuthUser(name = name, email = email, password = password)
            pref.register(authUser)
            toast("Register Success")
            navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}