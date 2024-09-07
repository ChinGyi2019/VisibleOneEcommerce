package com.vanzar.visibleone.core.design.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanzar.visibleone.core.design.theme.GRAY_50
import com.vanzar.visibleone.core.design.theme.White
import com.vanzar.visibleone.core.design.theme.dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontSize = 14.sp
    )
) {
    TextField(
        value = query,
        shape = MaterialTheme.shapes.extraLarge.copy(
            CornerSize(50)
        ),
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, GRAY_50.copy(alpha = 0.3f), RoundedCornerShape(32.dp))
            .background(Color.White, RoundedCornerShape(32.dp)),
        //.padding(dimensions.space8),
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        textStyle = textStyle,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun RoundedSearchBar(
//    searchText: TextFieldValue,
//    onSearchTextChange: (TextFieldValue) -> Unit,
//    placeholderText: String = "Search..."
//) {
//    TextField(
//        value = searchText,
//        onValueChange = onSearchTextChange,
//        placeholder = { Text(text = placeholderText) },
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.White, RoundedCornerShape(16.dp))
//            .padding(8.dp),
//        colors = TextFieldDefaults.textFieldColors(
//            textColor = Color.Black,
//            backgroundColor = Color.Transparent, // Remove background to use custom background
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//            placeholderColor = Color.Gray
//        ),
//        shape = RoundedCornerShape(16.dp)
//    )
//}