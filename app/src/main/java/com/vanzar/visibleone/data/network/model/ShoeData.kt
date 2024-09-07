package com.vanzar.visibleone.data.network.model

data class ShoeData(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val rating: Double? = null,
    val sizes: List<SizeData>? = null,
    val colors: List<ShoeColorData>? = null,
    val quantity: Int? = null,
    val label: String? = null,
    val image: String? = null
)

data class SizeData(
    val id: String? = null,
    val name: String? = null,
    val isSelected: Boolean? = null
)
data class ShoeColorData(
    val id: String? = null,
    val name: String? = null,
    val hexCode: String? = null,
    val isSelected: Boolean? = null
)