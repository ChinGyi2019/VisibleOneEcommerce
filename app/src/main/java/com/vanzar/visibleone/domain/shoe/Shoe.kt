package com.vanzar.visibleone.domain.shoe

import kotlinx.serialization.Serializable

@Serializable
data class Shoe(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val rating: String,
    val sizes: List<Size>,
    val colors: List<ShoeColor>,
    val quantity: Int,
    val label: String,
    val image: String
) {
    companion object {
        val dummyShoes = listOf(
            Shoe(
                id = "1",
                name = "Nike Air Vapormax 2020",
                description = "A lightweight and breathable shoe designed for maximum comfort.",
                price = "250.00",
                rating = "4.5",
                sizes = listOf(
                    Size(id = "1", name = "7"),
                    Size(id = "2", name = "8"),
                    Size(id = "3", name = "9")
                ),
                colors = listOf(
                    ShoeColor(id = "1", name = "White", hexCode = "#FFFFFF"),
                    ShoeColor(id = "2", name = "Blue", hexCode = "#0000FF"),
                    ShoeColor(id = "3", name = "Black", hexCode = "#000000")

                ),
                quantity = 10,
                label = "New",
                image = "https://example.com/images/nike_air_vapormax_2020.png"
            ),
            Shoe(
                id = "2",
                name = "Adidas Ultraboost",
                description = "A highly responsive running shoe with unmatched energy return.",
                price = "180.00",
                rating = "4.8",
                sizes = listOf(
                    Size(id = "4", name = "8"),
                    Size(id = "5", name = "9"),
                    Size(id = "6", name = "10")
                ),
                colors = listOf(
                    ShoeColor(id = "1", name = "White", hexCode = "#FFFFFF"),
                    ShoeColor(id = "2", name = "Blue", hexCode = "#0000FF"),
                    ShoeColor(id = "3", name = "Black", hexCode = "#000000")

                ),
                quantity = 15,
                label = "Best Seller",
                image = "https://example.com/images/adidas_ultraboost.png"
            ),
            Shoe(
                id = "3",
                name = "Puma RS-X3",
                description = "A retro-inspired sneaker with a bold and vibrant design.",
                price = "110.00",
                rating = "4.3",
                sizes = listOf(
                    Size(id = "7", name = "6"),
                    Size(id = "8", name = "7"),
                    Size(id = "9", name = "8")
                ),
                colors = listOf(
                    ShoeColor(id = "1", name = "White", hexCode = "#FFFFFF"),
                    ShoeColor(id = "2", name = "Blue", hexCode = "#0000FF"),
                    ShoeColor(id = "3", name = "Black", hexCode = "#000000")

                ),
                quantity = 8,
                label = "Limited Edition",
                image = "https://example.com/images/puma_rsx3.png"
            ),
            Shoe(
                id = "4",
                name = "Reebok Zig Kinetica",
                description = "A dynamic shoe designed for high performance with cutting-edge technology.",
                price = "130.00",
                rating = "4.6,",
                sizes = listOf(
                    Size(id = "10", name = "9"),
                    Size(id = "11", name = "10"),
                    Size(id = "12", name = "11")
                ),
                colors = emptyList(),
                quantity = 12,
                label = "Trending",
                image = "https://example.com/images/reebok_zig_kinetica.png"
            )
        )
    }
}

@Serializable
data class Size(
    val id: String,
    val name: String,
    val isSelected: Boolean = false
) {
    companion object {
        val dummyShoeSizes = listOf(
            Size(id = "1", name = "6"),
            Size(id = "2", name = "7"),
            Size(id = "3", name = "8"),
            Size(id = "4", name = "9"),
            Size(id = "5", name = "10"),
            Size(id = "6", name = "11"),
            Size(id = "7", name = "12")
        )
    }
}

@Serializable
data class ShoeColor(
    val id: String,
    val name: String,
    val hexCode: String,
    val isSelected: Boolean = false
)

