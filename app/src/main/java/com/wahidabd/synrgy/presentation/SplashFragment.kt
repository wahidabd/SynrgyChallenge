package com.wahidabd.synrgy.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.wahidabd.synrgy.databinding.FragmentSplashBinding
import com.wahidabd.synrgy.utils.SharedPreferences
import com.wahidabd.synrgy.utils.navigateArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class SplashFragment : Fragment() {

    private val pref: SharedPreferences by inject()

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delaySplash()
        }
    }

    private suspend fun delaySplash() {
        delay(1200)
        if (pref.getLogin()) navigateArgs(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        else navigateArgs(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }

}