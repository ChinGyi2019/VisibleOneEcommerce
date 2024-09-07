package com.vanzar.visibleone.feature.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.vanzar.visibleone.R
import com.vanzar.visibleone.core.design.common.SearchBar
import com.vanzar.visibleone.core.design.theme.Black
import com.vanzar.visibleone.core.design.theme.GRAY_50
import com.vanzar.visibleone.core.design.theme.Gray
import com.vanzar.visibleone.core.design.theme.Orange
import com.vanzar.visibleone.core.design.theme.White
import com.vanzar.visibleone.core.design.theme.dimensions
import com.vanzar.visibleone.domain.brand.Brand.Companion.dummyShoeBrands
import com.vanzar.visibleone.domain.shoe.Shoe
import com.vanzar.visibleone.domain.shoe.Shoe.Companion.dummyShoes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    goToDetail: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState: HomScreenState by
    viewModel.uiState.collectAsState(initial = HomScreenState())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = "cart"
                            )
                        }
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White,
                    titleContentColor = Black
                )
            )
        }, containerColor = White
    ) {
        val list = if (uiState.query.isNotEmpty() && uiState.searchShoes.isNotEmpty())
            uiState.searchShoes else uiState.shoeList
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(top = 0.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                SearchBar(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(60.dp),
                    query = uiState.query,
                    onQueryChange = viewModel::searchItem,
                )
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(dummyShoeBrands.size) { BrandItem() }
                }
            }

            if (uiState.loading) {
                items(7) {
                    PopularLoadingItem()
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp
                        )
                        .padding(top = 16.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Popular",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = White,
                            contentColor = Black
                        ),
                        modifier = Modifier
                            //.size(28.dp)
                            .border(1.dp, GRAY_50, CircleShape),
                        onClick = {
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.List, contentDescription = "")
                    }
                }
            }
            items(list) { shoe ->
                PopularItem(
                    shoe = shoe,
                    onAddToCartClick = { },
                    onClickCard = {
                        goToDetail(it.id)
                    },
                    onFavoriteClick = { /* Handle favorite */ }
                )
            }
        }
    }
}

@Composable
fun BrandItem(
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("painter.icon")
            .size(Size.ORIGINAL)
            .error(R.drawable.nike_logo)
            .build()
    )
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = GRAY_50.copy(alpha = 0.2f)
        )
    ) {
        Image(
            painter,
            modifier = Modifier
                .width(70.dp)
                .height(50.dp)
                .padding(
                    horizontal = 20.dp,
                    vertical = 12.dp
                ),
            contentDescription = ""
        )
    }
}

@Composable
fun PopularLoadingItem() {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite-transition")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
                0.7f at 500
            },
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha-anim"
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .height(160.dp)
            .padding(horizontal = dimensions.space16)
            .clip(RoundedCornerShape(16.dp))
            .background(Gray.copy(alpha = alpha))

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(
                        color = GRAY_50.copy(alpha = alpha)
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(16.dp)
                    .background(
                        color = GRAY_50.copy(alpha = alpha)
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(16.dp)
                    .background(
                        color = GRAY_50.copy(alpha = alpha)
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(16.dp)
                    .background(
                        color = GRAY_50.copy(alpha = alpha)
                    )
            )
        }
    }
}

@Composable
fun PopularItem(
    shoe: Shoe,
    onClickCard: (Shoe) -> Unit,
    onAddToCartClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val ivBackDrop = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(shoe.image)
            .size(Size.ORIGINAL)
            .error(R.drawable.dummy_red_shoe)// Set the target size to load the image at.
            .build()
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .height(160.dp)
            .padding(horizontal = dimensions.space16)
            .clip(RoundedCornerShape(16.dp))
            .background(GRAY_50.copy(alpha = 0.2f))
            .clickable {
                onClickCard(shoe)
            }
    ) {
        Image(
            painter = ivBackDrop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .padding(24.dp),
            contentScale = ContentScale.Inside
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 16.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$${shoe.price}",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                IconButton(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
                    onClick = onFavoriteClick
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        // painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = null,
                        tint = Orange
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = shoe.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Black,
                        contentColor = White
                    ),
                    modifier = Modifier
                        .size(32.dp),
                    onClick = onAddToCartClick
                ) {
                    Icon(
                        modifier = Modifier.padding(4.dp),
                        painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = null,
                        tint = White
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PopularItemPreview() {
    MaterialTheme {
        PopularItem(
            shoe = dummyShoes.first(),
            onClickCard = {},
            onAddToCartClick = { /*TODO*/ }) {

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewStartScreen() {
    MaterialTheme {
        HomeScreen({})
    }
}