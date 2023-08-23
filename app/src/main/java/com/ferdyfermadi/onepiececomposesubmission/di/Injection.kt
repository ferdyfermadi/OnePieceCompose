package com.ferdyfermadi.onepiececomposesubmission.di

import com.ferdyfermadi.onepiececomposesubmission.data.OnePieceRepository

object Injection {
    fun provideRepository(): OnePieceRepository {
        return OnePieceRepository.getInstance()
    }
}