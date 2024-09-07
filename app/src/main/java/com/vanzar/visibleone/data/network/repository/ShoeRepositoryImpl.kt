package com.vanzar.visibleone.data.network.repository

import com.vanzar.visibleone.data.network.dataSource.ShoeDataSource
import com.vanzar.visibleone.domain.shoe.Shoe
import com.vanzar.visibleone.domain.shoe.repository.ShoeRepository
import javax.inject.Inject

class ShoeRepositoryImpl @Inject constructor(
    private val shoeDataSource: ShoeDataSource
) : ShoeRepository {
    override suspend fun getPopularShoes(): List<Shoe> {
        return shoeDataSource.getPopularShoes()
    }

    override suspend fun getShoeByID(id: String): Shoe? {
        return shoeDataSource.getShoeByID(id)
    }
}