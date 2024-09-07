package com.vanzar.visibleone.di

import com.smh.network.createService
import com.vanzar.visibleone.data.network.services.ShoeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun provideShoeService(retrofit: Retrofit): ShoeService {
        return retrofit.createService()
    }
}