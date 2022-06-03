package uz.texnopos.berdaqgargabayuli.presenter.main.lifestyle.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle
import uz.texnopos.berdaqgargabayuli.domain.MainRepository
import uz.texnopos.berdaqgargabayuli.utils.Resource

class LifeStyleInfoViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var _lifeStyleInfo: MutableLiveData<Resource<LifeStyle>> = MutableLiveData()
    val lifeStyleInfo: LiveData<Resource<LifeStyle>> get() = _lifeStyleInfo

    fun getLifeData(id: Int) {
        _lifeStyleInfo.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = mainRepository.getLifeAgeInfo(id)
                _lifeStyleInfo.value = Resource.success(result)
            } catch (e: Exception) {
                _lifeStyleInfo.value = Resource.error(e.message.toString())
            }
        }
    }
}