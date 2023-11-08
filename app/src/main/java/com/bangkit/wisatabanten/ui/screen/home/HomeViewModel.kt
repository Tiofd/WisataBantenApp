package com.bangkit.wisatabanten.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.wisatabanten.data.TourRepository
import com.bangkit.wisatabanten.model.BantenTourism
import com.bangkit.wisatabanten.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TourRepository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<BantenTourism>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<BantenTourism>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query


    fun search(newQuery: String) {
        _query.value = newQuery
        _uiState.value = UiState.Success(repository.findTour(_query.value))
    }


    fun getAllRecipe() {
        viewModelScope.launch {
            repository.getAllTour()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { listRecipe ->
                    _uiState.value = UiState.Success(listRecipe)
                }
        }
    }

}