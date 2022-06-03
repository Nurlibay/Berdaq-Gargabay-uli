package uz.texnopos.berdaqgargabayuli.presenter.main.creativity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.texnopos.berdaqgargabayuli.data.entity.Creativity
import uz.texnopos.berdaqgargabayuli.domain.MainRepository
import uz.texnopos.berdaqgargabayuli.utils.Resource

class CreativityViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var _creativityInfo: MutableLiveData<Resource<Creativity>> = MutableLiveData()
    val creativityInfo: LiveData<Resource<Creativity>> get() = _creativityInfo

    fun getCreativityInfo() {
        _creativityInfo.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = mainRepository.getCreativityInfo()
                _creativityInfo.value = Resource.success(result)
            } catch (e: Exception) {
                _creativityInfo.value = Resource.error(e.message.toString())
            }
        }
    }

}