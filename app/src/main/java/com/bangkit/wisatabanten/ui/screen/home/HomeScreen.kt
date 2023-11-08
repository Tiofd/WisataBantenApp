package com.bangkit.wisatabanten.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.wisatabanten.model.BantenTourism
import com.bangkit.wisatabanten.model.TourSource
import com.bangkit.wisatabanten.ui.ViewModelFactory
import com.bangkit.wisatabanten.ui.common.UiState
import com.bangkit.wisatabanten.ui.component.CardTour
import com.bangkit.wisatabanten.ui.component.SearchBar
import com.bangkit.wisatabanten.ui.theme.WisataBantenTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory()),
    navigateToDetail: (String) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllRecipe()
            }
            is UiState.Success -> {
                HomeContent(
                    listTour = uiState.data,
                    navigateToDetail = navigateToDetail,
                    modifier = modifier,
                    viewModel = viewModel,
                )
            }
            is UiState.Error -> {
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    listTour: List<BantenTourism>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
) {
    Box(modifier) {
        Column(modifier.fillMaxSize()) {
            val query by viewModel.query
            SearchBar(
                query = query,
                onQueryChange = viewModel::search,
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            )
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listTour) {
                    CardTour(
                        image = it.tour.image,
                        title = it.tour.title,
                        modifier = Modifier
                            .clickable { navigateToDetail(it.tour.id) }
                            .animateItemPlacement(tween(durationMillis = 100))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    WisataBantenTheme {
        HomeContent(
            listTour = listOf(
                BantenTourism(TourSource.tours[0], false),
                BantenTourism(TourSource.tours[1], false),
                BantenTourism(TourSource.tours[1], false),
                BantenTourism(TourSource.tours[2], false),
                BantenTourism(TourSource.tours[3], false),
                BantenTourism(TourSource.tours[3], false),
                BantenTourism(TourSource.tours[1], false),
                BantenTourism(TourSource.tours[2], false),
                BantenTourism(TourSource.tours[2], false),
                BantenTourism(TourSource.tours[1], false),
            ),
            navigateToDetail = {},
            viewModel = viewModel(factory = ViewModelFactory())
        )
    }
}