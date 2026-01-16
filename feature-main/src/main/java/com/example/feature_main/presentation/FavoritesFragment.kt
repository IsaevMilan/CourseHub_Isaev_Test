package com.example.feature_main.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.base.BaseFragment
import com.example.core.di.AppDependenciesProvider
import com.example.feature_auth.databinding.FragmentFavoriteBinding
import com.example.feature_main.di.DaggerMainComponent
import com.example.feature_main.presentation.adapter.courseAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch

class FavoritesFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private val viewModel: MainViewModel by lazy {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val provider = requireActivity() as MainComponentProvider
                return provider.getMainComponent().getViewModel() as T
            }
        }
        ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
    }

    private val adapter = ListDelegationAdapter(courseAdapterDelegate { })

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

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.courses.collect { list ->
                val fav = list.filter { it.hasLike }
                Log.d("FavoritesFragment", "Фильтруем избранное: ${fav.map { it.id }}")
                adapter.items = fav
                adapter.notifyDataSetChanged()
            }
        }

        /* viewLifecycleOwner.lifecycleScope.launch {
             viewModel.courses.collect { list ->

                 // только избранное
                 adapter.items = list.filter { it.hasLike }
                 adapter.notifyDataSetChanged()
             }
         }*/

        viewModel.fakeLoading()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { loading ->
                binding.progressView.root.visibility =
                    if (loading) View.VISIBLE else View.GONE
            }
        }
    }
}