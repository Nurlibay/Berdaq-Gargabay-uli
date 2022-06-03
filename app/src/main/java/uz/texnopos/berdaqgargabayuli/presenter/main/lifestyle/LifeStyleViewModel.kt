package uz.texnopos.berdaqgargabayuli.presenter.main.lifestyle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle
import uz.texnopos.berdaqgargabayuli.domain.MainRepository
import uz.texnopos.berdaqgargabayuli.utils.Resource

class LifeStyleViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var _lifeStyle: MutableLiveData<Resource<List<LifeStyle>>> = MutableLiveData()
    val lifeStyle: LiveData<Resource<List<LifeStyle>>> get() = _lifeStyle

    fun getLifeData() {
        _lifeStyle.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = mainRepository.getLifeData()
                _lifeStyle.value = Resource.success(result)
            } catch (e: Exception) {
                _lifeStyle.value = Resource.error(e.message.toString())
            }
        }
    }
}