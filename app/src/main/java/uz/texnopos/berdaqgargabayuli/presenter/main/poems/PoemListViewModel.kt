package uz.texnopos.berdaqgargabayuli.presenter.main.poems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.texnopos.berdaqgargabayuli.data.entity.Poems
import uz.texnopos.berdaqgargabayuli.domain.MainRepository
import uz.texnopos.berdaqgargabayuli.utils.Resource

class PoemListViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var _poem: MutableLiveData<Resource<List<Poems>>> = MutableLiveData()
    val poem: LiveData<Resource<List<Poems>>> get() = _poem

    fun getPoems() {
        _poem.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = mainRepository.getPoems()
                _poem.value = Resource.success(result)
            } catch (e: Exception) {
                _poem.value = Resource.error(e.message.toString())
            }
        }
    }

}