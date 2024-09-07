package com.vanzar.visibleone.data.network.mapper

import com.vanzar.visibleone.domain.shoe.Shoe
import com.vanzar.visibleone.domain.shoe.ShoeColor
import com.vanzar.visibleone.domain.shoe.Size

@Suppress("UNCHECKED_CAST")
fun Map<String, Any?>.toShoeModel(): Shoe = Shoe(
    id = get("id") as? String ?: "",
    name = get("name") as? String ?: "",
    description = get("description") as? String ?: "",
    price = get("price") as? String ?: "0.0",
    rating = get("rating") as? String ?: "0.0",
    sizes = get("sizes") as? List<Size> ?: emptyList(),
    colors = get("colors") as? List<ShoeColor> ?: emptyList(),
    quantity = get("quantity") as? Int ?: 0,
    label = get("label") as? String ?: "",
    image = get("image") as? String ?: ""
)