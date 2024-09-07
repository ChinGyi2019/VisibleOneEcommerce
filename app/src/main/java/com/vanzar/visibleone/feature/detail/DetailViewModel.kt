package com.vanzar.visibleone.feature.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanzar.visibleone.domain.shoe.Shoe
import com.vanzar.visibleone.domain.shoe.ShoeColor
import com.vanzar.visibleone.domain.shoe.Size
import com.vanzar.visibleone.domain.shoe.repository.ShoeRepository
import com.vanzar.visibleone.feature.home.HomScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailScreenState(
    val loading: Boolean = false,
    val shoe: Shoe? = null,
    val selectedColor: String = "",
    val selectedSize: String = ""
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val shoeRepository: ShoeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState = _uiState.asStateFlow()

    suspend fun getShoeDetail(id: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(loading = true)
            }
            shoeRepository.getShoeByID(id)?.let { shoe ->
                _uiState.update {
                    it.copy(
                        shoe = shoe.copy(
                            sizes = Size.dummyShoeSizes,
                            colors = dummyColor
                        ), loading = false
                    )
                }
            }
        }
    }

    fun updateSelectedColor(color: ShoeColor) {
        _uiState.update {
            it.copy(
                selectedColor = color.id,
            )
        }
    }

    fun updateQuantity(quantity: Int) {
        if (quantity < 0) {
            return
        }
        _uiState.update {
            it.copy(
                shoe = _uiState.value.shoe?.copy(
                    quantity = quantity
                )
            )
        }
    }

    fun updateSelectedSize(size: Size) {
        _uiState.update {
            it.copy(
                selectedSize = size.id,
            )
        }
    }
}