package com.example.feature_main.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.base.BaseFragment
import com.example.core.di.AppDependenciesProvider
import com.example.feature_auth.databinding.FragmentMainBinding
import com.example.feature_main.di.DaggerMainComponent
import com.example.feature_main.presentation.adapter.courseAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by lazy {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val provider = requireActivity() as MainComponentProvider
                return provider.getMainComponent().getViewModel() as T
            }
        }
        ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
    }

    // Инициализируем адаптер через делегат
    private val adapter = ListDelegationAdapter(
        courseAdapterDelegate { course ->
            Log.d("MainFr", "Нажали на лайк ${course.id} hasLike=${course.hasLike}")
            viewModel.toggleLike(course.id)
        }
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies =
            (requireActivity().application as AppDependenciesProvider).getDependencies()

        DaggerMainComponent.builder()
            .appDependencies(dependencies)
            .build()
            .inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCourses.adapter = adapter

        // Кнопка сортировки по ТЗ
        binding.btnSort.setOnClickListener {
            viewModel.sortCourses()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.courses.collect { list ->
                adapter.items = list
                adapter.notifyDataSetChanged()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { loading ->
                binding.progressView.root.visibility =
                    if (loading) View.VISIBLE else View.GONE
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
