package kz.dream.marketplace.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kz.dream.marketplace.entity.ItemEntity
import kz.dream.marketplace.usecase.AddToFavoritesUseCase
import kz.dream.marketplace.usecase.GetCatalogUseCase
import kz.dream.marketplace.usecase.GetFavoriteProductsUseCase
import kz.dream.marketplace.usecase.RemoveFromFavoritesUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : ViewModel() {

    var catalog: StateFlow<PagingData<ItemEntity>> = MutableStateFlow(value = PagingData.empty())
        private set

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            catalog = getCatalogUseCase.invoke().stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
        }
    }

    fun addToFavorite(product: ItemEntity) {
        viewModelScope.launch { addToFavoritesUseCase(product = product) }
    }

    fun removeFromFavorite(product: ItemEntity) {
        viewModelScope.launch { removeFromFavoritesUseCase(product = product) }
    }
}

