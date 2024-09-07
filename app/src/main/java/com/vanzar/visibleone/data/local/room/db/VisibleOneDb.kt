package com.vanzar.visibleone.data.local.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vanzar.visibleone.data.local.room.dao.ShoeDao
import com.vanzar.visibleone.data.local.room.entity.ShoeEntity

@Database(entities = [ShoeEntity::class], version = 1, exportSchema = false)
abstract class VisibleOneDb : RoomDatabase() {

    companion object {
        fun createTestDb(
            context: Context
        ): VisibleOneDb {
            return Room.inMemoryDatabaseBuilder(
                context = context,
                VisibleOneDb::class.java
            )
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun shoeDao(): ShoeDao
}