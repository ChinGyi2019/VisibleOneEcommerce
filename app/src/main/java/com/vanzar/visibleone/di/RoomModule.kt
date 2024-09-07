package com.vanzar.visibleone.di

import android.content.Context
import androidx.room.Room
import com.vanzar.visibleone.data.local.room.db.VisibleOneDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun providesDB(
        @ApplicationContext context: Context
    ): VisibleOneDb = Room.databaseBuilder(
        context,
        VisibleOneDb::class.java,
        "shoe-db"
    ).build()

    @Provides
    @Singleton
    fun providesPortfolioDao(
        db: VisibleOneDb
    ) = db.shoeDao()
}