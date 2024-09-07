package com.vanzar.visibleone.domain.shoe.repository

import com.vanzar.visibleone.domain.shoe.Shoe

interface ShoeRepository {
    suspend fun getPopularShoes(): List<Shoe>
    suspend fun getShoeByID(id: String): Shoe?
}