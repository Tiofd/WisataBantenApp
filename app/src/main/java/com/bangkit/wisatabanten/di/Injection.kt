package com.bangkit.wisatabanten.di

import com.bangkit.wisatabanten.data.TourRepository

object Injection {
    fun provideRepository(): TourRepository {
        return TourRepository.getInstance()
    }
}