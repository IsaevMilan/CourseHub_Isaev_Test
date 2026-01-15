package com.example.feature_auth.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseFragment
import com.example.core.di.AppDependenciesProvider
import com.example.feature_auth.R
import com.example.feature_auth.databinding.FragmentAuthBinding
import com.example.feature_auth.di.DaggerAuthComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    // 1. Инжектим саму ViewModel (Dagger найдет её через AuthComponent)
    @Inject
    lateinit var viewModel: AuthViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 2. Инициализация Dagger компонента фичи
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
        // Слушаем ввод Email
        binding.etEmail.doAfterTextChanged { text ->
            val input = text.toString()
            viewModel.onEmailChanged(input)

            // Если пользователь ввел кириллицу, мы можем визуально это поправить или просто не пускать в стейт
            if (input != text.toString()) {
                binding.etEmail.setText(input)
                binding.etEmail.setSelection(input.length)
            }
        }

        // Слушаем ввод пароля
        binding.etPassword.doAfterTextChanged { text ->
            viewModel.onPasswordChanged(text.toString())
        }

        // Кнопка входа
        binding.btnLogin.setOnClickListener {
            // В тестовом задании обычно просто переход, если кнопка активна
//            findNavController().navigate(R.id.action_auth_to_main)
        }

        // Соцсети
        binding.btnVk.setOnClickListener {
            openBrowser("https://vk.com/")
        }

        binding.btnOk.setOnClickListener {
            openBrowser("https://ok.ru/")
        }

        // Кнопки-заглушки (по ТЗ они неактивны, но можем просто тосты повесить)
        binding.tvRegister.setOnClickListener { /* Неактивно по ТЗ */ }
        binding.tvForgotPassword.setOnClickListener { /* Неактивно по ТЗ */ }
    }

    private fun observeViewModel() {
        // 3. Подписываемся на Flow состояния кнопки
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoginEnabled.collect { isEnabled ->
                    binding.btnLogin.isEnabled = isEnabled
                    // Меняем прозрачность, чтобы визуально было понятно, что кнопка выключена
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
            // На случай, если в эмуляторе нет браузера
        }
    }
}