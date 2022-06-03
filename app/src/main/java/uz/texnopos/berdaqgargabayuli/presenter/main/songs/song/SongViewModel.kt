package uz.texnopos.berdaqgargabayuli.presenter.main.songs.song

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.texnopos.berdaqgargabayuli.data.entity.Songs
import uz.texnopos.berdaqgargabayuli.domain.MainRepository
import uz.texnopos.berdaqgargabayuli.utils.Resource

class SongViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var _song: MutableLiveData<Resource<Songs>> = MutableLiveData()
    val song: LiveData<Resource<Songs>> get() = _song

    fun getSongById(id: Int) {
        _song.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = mainRepository.getSongById(id)
                _song.value = Resource.success(result)
            } catch (e: Exception) {
                _song.value = Resource.error(e.message.toString())
            }
        }
    }
}