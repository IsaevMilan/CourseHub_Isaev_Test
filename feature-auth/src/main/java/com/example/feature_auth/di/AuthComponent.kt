package com.example.feature_auth.di

import com.example.core.di.AppDependencies
import com.example.feature_auth.presentation.AuthFragment
import dagger.Component

@Component(dependencies = [AppDependencies::class])
interface AuthComponent {
    fun inject(fragment: AuthFragment)

    @Component.Builder
    interface Builder {
        fun appDependencies(dependencies: AppDependencies): Builder
        fun build(): AuthComponent
    }
}
