package com.example.feature_main.di

import com.example.core.di.AppDependencies
import com.example.feature_main.presentation.FavoritesFragment
import com.example.feature_main.presentation.MainFragment
import com.example.feature_main.presentation.MainViewModel
import dagger.Component

@MainScope
@Component(dependencies = [AppDependencies::class])
interface MainComponent {
    fun inject(fragment: MainFragment)
    fun inject(fragment: FavoritesFragment)
    fun getViewModel(): MainViewModel

    @Component.Builder
    interface Builder {
        fun appDependencies(dependencies: AppDependencies): Builder
        fun build(): MainComponent
    }
}
