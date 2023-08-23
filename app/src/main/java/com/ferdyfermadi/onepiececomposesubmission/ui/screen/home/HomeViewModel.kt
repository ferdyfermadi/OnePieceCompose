package com.ferdyfermadi.onepiececomposesubmission.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ferdyfermadi.onepiececomposesubmission.data.OnePieceRepository
import com.ferdyfermadi.onepiececomposesubmission.model.OnePiece
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val repository: OnePieceRepository
) : ViewModel() {
    private val _groupedMugiwara = MutableStateFlow(
        repository.getPirate()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedMugiwara: StateFlow<Map<Char, List<OnePiece>>> get() = _groupedMugiwara

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedMugiwara.value = repository.searchPirate(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}