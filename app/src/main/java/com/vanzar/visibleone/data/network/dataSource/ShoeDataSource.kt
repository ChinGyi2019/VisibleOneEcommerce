package com.vanzar.visibleone.data.network.dataSource

import com.vanzar.visibleone.domain.shoe.Shoe

interface ShoeDataSource {
    suspend fun getPopularShoes(): List<Shoe>
    suspend fun getShoeByID(id: String): Shoe?
}