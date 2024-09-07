package com.vanzar.visibleone.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanzar.visibleone.domain.shoe.Shoe
import com.vanzar.visibleone.domain.shoe.repository.ShoeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomScreenState(
    val loading: Boolean = false,
    val query: String = "",
    val shoeList: List<Shoe> = emptyList(),
    val searchShoes: List<Shoe> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shoeRepository: ShoeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(loading = true)
            }
            shoeRepository.getPopularShoes().let { shoes ->
                // Log.e("shoes", shoes.toString())
                _uiState.update {
                    it.copy(
                        shoeList = shoes,
                        loading = false
                    )
                }
            }
        }
    }

    fun searchItem(query: String) {
        val searchShoes = _uiState.value.shoeList.filter {
            it.name.lowercase().contains(query.lowercase()) ||
                    it.price.lowercase().contains(query.lowercase()) ||
                    it.label.lowercase().contains(query.lowercase())
        }
        _uiState.update {
            it.copy(
                query = query,
                searchShoes = searchShoes
            )
        }
    }
}