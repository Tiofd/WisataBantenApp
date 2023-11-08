package com.bangkit.wisatabanten.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.wisatabanten.R
import com.bangkit.wisatabanten.ui.ViewModelFactory
import com.bangkit.wisatabanten.ui.common.UiState
import com.bangkit.wisatabanten.ui.theme.WisataBantenTheme


@Composable
fun DetailScreen(
    tourId: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory()
    ),
    onNavigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getTourById(tourId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.tour.image,
                    data.tour.title,
                    data.tour.description,
                    onBackClick = onNavigateBack,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title : String,
    description : String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image (
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon (
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )
            Text(
                text = description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify,
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    WisataBantenTheme() {
        DetailContent(
            R.drawable.pantai_sawarna,
            "Pantai Sawarna",
            "Lorem Ipsum Lorem Ipsum",
            onBackClick = {},
        )
    }
}