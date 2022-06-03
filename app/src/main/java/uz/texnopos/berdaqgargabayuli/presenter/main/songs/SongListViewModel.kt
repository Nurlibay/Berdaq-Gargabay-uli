package uz.texnopos.berdaqgargabayuli.presenter.main.songs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.texnopos.berdaqgargabayuli.data.entity.Songs
import uz.texnopos.berdaqgargabayuli.domain.MainRepository
import uz.texnopos.berdaqgargabayuli.utils.Resource

class SongListViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var _songs: MutableLiveData<Resource<List<Songs>>> = MutableLiveData()
    val songs: LiveData<Resource<List<Songs>>> get() = _songs

    fun getSongs() {
        _songs.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = mainRepository.getSongs()
                _songs.value = Resource.success(result)
            } catch (e: Exception) {
                _songs.value = Resource.error(e.message.toString())
            }
        }
    }
}