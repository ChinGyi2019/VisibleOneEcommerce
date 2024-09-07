package com.vanzar.visibleone.domain.brand

import androidx.annotation.DrawableRes
import com.vanzar.visibleone.R

data class Brand(
    val id: String,
    @DrawableRes val icon: Int? = null,
    val name: String,
    val isSelected: Boolean,
    val description: String
) {
    companion object {
        val dummyShoeBrands = listOf(
            Brand(
                id = "brand1",
                // icon = R.drawable.shoe_icon_1, // replace with actual resource ID
                name = "Speedster",
                isSelected = false,
                description = "High-performance shoes for runners."
            ),
            Brand(
                id = "brand2",
                // icon = R.drawable.shoe_icon_2, // replace with actual resource ID
                name = "UrbanFlex",
                isSelected = true,
                description = "Comfortable and stylish shoes for urban environments."
            ),
            Brand(
                id = "brand3",
               // icon = R.drawable.shoe_icon_3, // replace with actual resource ID
                name = "MountainGear",
                isSelected = false,
                description = "Rugged shoes built for outdoor adventures."
            ),
            Brand(
                id = "brand4",
                // icon = R.drawable.shoe_icon_4, // replace with actual resource ID
                name = "ClassicStep",
                isSelected = false,
                description = "Timeless design with modern comfort."
            ),
            Brand(
                id = "brand5",
               // icon = R.drawable.shoe_icon_5, // replace with actual resource ID
                name = "EcoWalk",
                isSelected = true,
                description = "Eco-friendly shoes made from sustainable materials."
            ))
    }
}