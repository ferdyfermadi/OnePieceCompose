package com.ferdyfermadi.onepiececomposesubmission.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferdyfermadi.onepiececomposesubmission.common.UiState
import com.ferdyfermadi.onepiececomposesubmission.data.OnePieceRepository
import com.ferdyfermadi.onepiececomposesubmission.model.OnePiece
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: OnePieceRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<OnePiece>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OnePiece>>
        get() = _uiState


    fun getId(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getIdPirate(id))
        }
    }
}