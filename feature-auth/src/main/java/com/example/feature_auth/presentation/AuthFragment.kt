package com.example.feature_auth.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseFragment
import com.example.core.di.AppDependenciesProvider
import com.example.feature_auth.databinding.FragmentAuthBinding
import com.example.feature_auth.di.DaggerAuthComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    @Inject
    lateinit var viewModel: AuthViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = (requireActivity().application as AppDependenciesProvider).getDependencies()

        DaggerAuthComponent.builder()
            .appDependencies(dependencies)
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        //  Email
        binding.etEmail.doAfterTextChanged { text ->
            val input = text.toString()
            viewModel.onEmailChanged(input)

            if (input != text.toString()) {
                binding.etEmail.setText(input)
                binding.etEmail.setSelection(input.length)
            }
        }

        // Слушаем ввод пароля
        binding.etPassword.doAfterTextChanged { text ->
            viewModel.onPasswordChanged(text.toString())
        }

        binding.btnLogin.setOnClickListener {
            val actionId =
                resources.getIdentifier("action_auth_to_main", "id", requireContext().packageName)
            if (actionId != 0) {
                findNavController().navigate(actionId)
            }
        }

        binding.btnVk.setOnClickListener {
            openBrowser("https://vk.com/")
        }

        binding.btnOk.setOnClickListener {
            openBrowser("https://ok.ru/")
        }

        binding.tvRegister.setOnClickListener { /* Неактивно по ТЗ */ }
        binding.tvForgotPassword.setOnClickListener { /* Неактивно по ТЗ */ }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoginEnabled.collect { isEnabled ->
                    binding.btnLogin.isEnabled = isEnabled
                    binding.btnLogin.alpha = if (isEnabled) 1.0f else 0.5f
                }
            }
        }
    }

    private fun openBrowser(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(),"Что-то пошло не так", Toast.LENGTH_SHORT).show()
        }
    }
}