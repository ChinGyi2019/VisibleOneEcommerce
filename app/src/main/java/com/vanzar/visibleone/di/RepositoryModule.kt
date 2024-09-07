package com.vanzar.visibleone.di

import com.vanzar.visibleone.data.network.repository.ShoeRepositoryImpl
import com.vanzar.visibleone.domain.shoe.repository.ShoeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindShoeRepository(
        shoeRepository: ShoeRepositoryImpl
    ): ShoeRepository
}