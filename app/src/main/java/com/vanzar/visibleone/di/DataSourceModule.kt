package com.vanzar.visibleone.di

import com.vanzar.visibleone.data.network.dataSource.ShoeDataSource
import com.vanzar.visibleone.data.network.dataSource.ShoeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindShoeDataStore(
        localDataStoreImpl: ShoeDataSourceImpl
    ): ShoeDataSource
}