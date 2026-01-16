package com.example.feature_main.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.feature_auth.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.core.base.BaseFragment
import com.example.core.di.AppDependenciesProvider
import com.example.feature_auth.databinding.FragmentFavoriteBinding
import com.example.feature_main.di.DaggerMainComponent
import com.example.feature_main.presentation.adapter.courseAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    @Inject
    lateinit var viewModel: MainViewModel

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
                // только избранное
                adapter.items = list.filter { it.hasLike }
                adapter.notifyDataSetChanged()
            }
        }
    }
}