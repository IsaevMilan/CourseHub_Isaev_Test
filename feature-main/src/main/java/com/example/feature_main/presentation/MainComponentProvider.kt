package com.example.feature_main.presentation

import com.example.feature_main.di.MainComponent

interface MainComponentProvider {
    fun getMainComponent(): MainComponent
}