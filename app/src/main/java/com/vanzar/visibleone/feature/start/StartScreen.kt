package com.vanzar.visibleone.feature.start

import androidx.annotation.Dimension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vanzar.visibleone.R
import com.vanzar.visibleone.core.design.theme.Dimensions
import com.vanzar.visibleone.core.design.theme.Orange
import com.vanzar.visibleone.core.design.theme.White
import com.vanzar.visibleone.core.design.theme.dimensions
import com.vanzar.visibleone.feature.Screen

@Composable
fun StartScreen(
    goToHome: () -> Unit
) {

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.start_bg),
                contentScale = ContentScale.FillHeight,
                contentDescription = "bg-img"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .alpha(0.3f)
                    .background(Color.Gray)
                    .align(Alignment.BottomCenter)
            ) {}
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(
                        Alignment.BottomCenter
                    )
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.start_title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = White,
                        fontWeight = FontWeight.W700,
                        fontSize = 40.sp,
                        lineHeight = 48.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.height(dimensions.space16))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.start_description),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = White,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(
                    modifier = Modifier.height(
                        dimensions.space32
                    )
                )
                ElevatedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensions.space16)
                        .padding(bottom = dimensions.space32),
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange,
                        contentColor = White
                    ),
                    onClick = goToHome,
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.action_start),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W700
                        )
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewStartScreen() {
    MaterialTheme {
        StartScreen({})
    }
}