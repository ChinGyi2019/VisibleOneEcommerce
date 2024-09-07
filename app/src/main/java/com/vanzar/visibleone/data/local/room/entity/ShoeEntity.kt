package com.vanzar.visibleone.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("ShoeEntity")
data class ShoeEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    )