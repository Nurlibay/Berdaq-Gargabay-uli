package uz.texnopos.berdaqgargabayuli.presenter.main.poems.poem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.texnopos.berdaqgargabayuli.data.entity.Poems
import uz.texnopos.berdaqgargabayuli.domain.MainRepository
import uz.texnopos.berdaqgargabayuli.utils.Resource

class PoemViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var _poem: MutableLiveData<Resource<Poems>> = MutableLiveData()
    val poem: LiveData<Resource<Poems>> get() = _poem

    fun getPoemById(id: Int) {
        _poem.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = mainRepository.getPoemById(id)
                _poem.value = Resource.success(result)
            } catch (e: Exception) {
                _poem.value = Resource.error(e.message.toString())
            }
        }
    }
}