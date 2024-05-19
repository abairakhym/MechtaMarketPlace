package kz.dream.marketplace.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kz.dream.marketplace.entity.ItemEntity
import kz.dream.marketplace.ui.MechtaState
import kz.dream.marketplace.usecase.AddToFavoritesUseCase
import kz.dream.marketplace.usecase.GetFavoriteProductsUseCase
import kz.dream.marketplace.usecase.RemoveFromFavoritesUseCase
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : ViewModel() {

    private var _favoriteState = MutableLiveData<MechtaState>()
    val favoriteState: LiveData<MechtaState>
        get() = _favoriteState

    fun getFavoriteProducts() {
        viewModelScope.launch {
            _favoriteState.value = MechtaState.Loading
            val result = getFavoriteProductsUseCase.invoke()

            if (result.isNotEmpty()) {
                _favoriteState.value = MechtaState.Success(result)
            } else {
                _favoriteState.value = MechtaState.Error("The db is empty")
            }
        }
    }

    fun removeFromFavorites(product: ItemEntity) {
        viewModelScope.launch { removeFromFavoritesUseCase(product) }
    }
}