package com.vanzar.visibleone.feature.detail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Delete
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.vanzar.visibleone.R
import com.vanzar.visibleone.core.design.extension.nonScaledSp
import com.vanzar.visibleone.core.design.theme.Black
import com.vanzar.visibleone.core.design.theme.GRAY_50
import com.vanzar.visibleone.core.design.theme.Gray
import com.vanzar.visibleone.core.design.theme.Orange
import com.vanzar.visibleone.core.design.theme.White
import com.vanzar.visibleone.core.design.theme.dimensions
import com.vanzar.visibleone.domain.shoe.Shoe
import com.vanzar.visibleone.domain.shoe.Shoe.Companion.dummyShoes
import com.vanzar.visibleone.domain.shoe.ShoeColor
import com.vanzar.visibleone.domain.shoe.Size
import com.vanzar.visibleone.domain.shoe.Size.Companion.dummyShoeSizes
import kotlinx.coroutines.delay

val dummyColor = listOf(
    ShoeColor(id = "1", name = "White", hexCode = "#FFFFFF"),
    ShoeColor(id = "2", name = "Blue", hexCode = "#0000FF"),
    ShoeColor(id = "3", name = "Black", hexCode = "#000000")
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    shoeId: String,
    viewModel: DetailViewModel = hiltViewModel(),
    clickFavourite: () -> Unit
) {
    LaunchedEffect(key1 = shoeId) {
        delay(300) // To avoid flickering when navigation
        viewModel.getShoeDetail(shoeId)
    }
    val uiState by
    viewModel.uiState.collectAsState(DetailScreenState())
    val context = LocalContext.current
    Scaffold(
        containerColor = White
    ) {
        AnimatedVisibility(
            visible = uiState.shoe != null,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = fadeOut()
        ) {
            uiState.shoe?.let { shoe ->
                val ivBackDrop = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(shoe.image)
                        .size(coil.size.Size.ORIGINAL)
                        .error(R.drawable.dummy_red_shoe)// Set the target size to load the image at.
                        .build()
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize()
                                .height(160.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(
                                        Alignment.TopCenter
                                    )
                                    .padding(horizontal = 16.dp, vertical = 24.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = White,
                                        contentColor = Black
                                    ),
                                    modifier = Modifier
                                        .size(40.dp)
                                        .border(1.dp, GRAY_50, CircleShape)
                                        .clip(CircleShape),
                                    onClick = {
                                        navController.popBackStack()
                                    }
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                        contentDescription = null,
                                        tint = Color.Black
                                    )
                                }
                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = White,
                                        contentColor = Orange
                                    ),
                                    modifier = Modifier
                                        .border(1.dp, GRAY_50, CircleShape)
                                        .size(40.dp),
                                    onClick = clickFavourite
                                ) {
                                    Icon(
                                        Icons.Filled.Favorite,
                                        // painter = painterResource(id = R.drawable.ic_cart),
                                        contentDescription = null,
                                        tint = Orange
                                    )
                                }
                            }
                            Image(
                                painter = ivBackDrop,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Inside
                            )
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = shoe.label,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Filled.Star,
                                    modifier = Modifier.size(24.dp),
                                    // painter = painterResource(id = R.drawable.ic_cart),
                                    contentDescription = null,
                                    tint = Orange
                                )
                                Text(
                                    text = shoe.rating,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            }
                        }

                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = shoe.name,
                                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "$${shoe.price}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.W700
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = "Size:",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.W700
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(
                                        text = "US",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.W700
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "UK",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.W400
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "EU",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.W400
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }

                            }
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(dummyShoeSizes) { size ->
                                    SizeItem(
                                        size.copy(
                                            isSelected = size.id == uiState.selectedSize
                                        ),
                                        onClickSize = {
                                            viewModel.updateSelectedSize(it)
                                        })
                                }
                            }
                        }

                    }
                    item { DescriptionItem("Description", shoe.description) }
                    item {
                        DescriptionItem(
                            "Free Delivery and Returns",
                            shoe.description
                        )
                    }
                    item {
                        Column(
                            modifier = Modifier.animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = "Size:",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.W700
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(
                                        text = "US",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.W700
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "UK",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.W700
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "EU",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.W700
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }

                            }
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(uiState.shoe?.colors ?: emptyList()) { color ->
                                    ColorItem(color,
                                        isSelected = uiState.selectedColor == color.id,
                                        onClickColor = {
                                            viewModel.updateSelectedColor(color)
                                        })
                                }
                            }
                        }

                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Quantity",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.W700
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                CompositionLocalProvider(
                                    LocalMinimumInteractiveComponentEnforcement provides false
                                ) {
                                    IconButton(
                                        colors = IconButtonDefaults.iconButtonColors(
                                            containerColor = White,
                                            contentColor = Black
                                        ),
                                        modifier = Modifier
                                            .size(32.dp)
                                            .border(1.dp, GRAY_50, CircleShape),
                                        onClick = {
                                            viewModel.updateQuantity(shoe.quantity.minus(1))
                                        }
                                    ) {
                                        Box(modifier = Modifier.fillMaxSize()) {
                                            Text(
                                                text = "-",
                                                modifier = Modifier.align(
                                                    Alignment.Center
                                                ),
                                                style = MaterialTheme.typography.bodyMedium.copy(
                                                    fontWeight = FontWeight.W700,
                                                    fontSize = 14.sp.nonScaledSp
                                                ),
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                    }
                                }
                                Text(
                                    text = shoe.quantity.toString(),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.W700
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                CompositionLocalProvider(
                                    LocalMinimumInteractiveComponentEnforcement provides false
                                ) {
                                    IconButton(
                                        colors = IconButtonDefaults.iconButtonColors(
                                            containerColor = White,
                                            contentColor = Black
                                        ),
                                        modifier = Modifier
                                            .size(32.dp)
                                            .border(1.dp, GRAY_50, CircleShape),
                                        onClick = {
                                            viewModel.updateQuantity(shoe.quantity.plus(1))
                                        }
                                    ) {
                                        Box(modifier = Modifier.fillMaxSize()) {
                                            Text(
                                                text = "+",
                                                modifier = Modifier.align(
                                                    Alignment.Center
                                                ),
                                                style = MaterialTheme.typography.bodyMedium.copy(
                                                    fontWeight = FontWeight.W700,
                                                    fontSize = 14.sp.nonScaledSp
                                                ),
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item {
                        ElevatedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = dimensions.space16)
                                .padding(bottom = dimensions.space24),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Orange,
                                contentColor = White
                            ),
                            onClick = {
                                Toast.makeText(context, "Added to cart!", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            },
                            contentPadding = PaddingValues(vertical = 16.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.action_add_to_cart),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.W700
                                ),
                            )
                        }
                    }
                }
            }
        }
        if (uiState.loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionItem(
    title: String,
    description: String
) {
    var isExpand by remember {
        mutableStateOf(false)
    }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                IconButton(
                    modifier = Modifier,
                    onClick = {
                        isExpand = !isExpand
                    }
                ) {
                    Icon(
                        if (!isExpand) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }
        AnimatedVisibility(isExpand) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.8f
                )
            )
        }
        // Spacer(modifier = Modifier.height(dimensions.space8))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun SizeItem(
    size: Size,
    onClickSize: (Size) -> Unit
) {
    Surface(
        color = if (size.isSelected) Orange else GRAY_50.copy(alpha = 0.2f),
        modifier = Modifier
            .width(60.dp)
            .height(40.dp),
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)),
        onClick = { onClickSize(size) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = size.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Center,
                    color = if (size.isSelected) White else MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}

@Composable
fun ColorItem(
    color: ShoeColor,
    isSelected: Boolean = false,
    onClickColor: (ShoeColor) -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .border(1.dp, GRAY_50, CircleShape)
            .background(hexToColor(color.hexCode))
            .clickable {
                onClickColor(color)
            }

    ) {
        if (isSelected) {
            Icon(
                Icons.Default.Check,
                modifier = Modifier.align(Alignment.Center),
                contentDescription = "check-ic", tint = Orange
            )
        }
    }
}

fun hexToColor(hexCode: String): Color {
    return Color(android.graphics.Color.parseColor(hexCode))
}

@Preview(showSystemUi = true)
@Composable
fun PreviewStartScreen() {
    MaterialTheme {
        DetailScreen(
            rememberNavController(),
            "",
            clickFavourite = {})
    }
}