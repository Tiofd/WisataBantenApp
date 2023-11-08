package com.bangkit.wisatabanten.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.wisatabanten.data.TourRepository
import com.bangkit.wisatabanten.model.BantenTourism
import com.bangkit.wisatabanten.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: TourRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<BantenTourism>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<BantenTourism>>
        get() = _uiState

    fun getTourById(tourId: String) {
        viewModelScope.launch {
            repository.getTourById(tourId)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { detailRecipe ->
                    _uiState.value = UiState.Success(detailRecipe)
                }
        }
    }
}