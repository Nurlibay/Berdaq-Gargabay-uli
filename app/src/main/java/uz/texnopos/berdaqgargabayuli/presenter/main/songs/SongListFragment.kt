package uz.texnopos.berdaqgargabayuli.presenter.main.songs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.databinding.FragmentSongsBinding
import uz.texnopos.berdaqgargabayuli.utils.ResourceState
import uz.texnopos.berdaqgargabayuli.utils.showMessage

class SongListFragment : Fragment(R.layout.fragment_songs) {

    private lateinit var binding: FragmentSongsBinding
    private val viewModel: SongListViewModel by viewModel()
    private val adapter = SongListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongsBinding.bind(view).apply {
            rvSongs.adapter = adapter
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        viewModel.getSongs()
        setupObserver()
        adapter.setOnClickItem {
            val action = SongListFragmentDirections.actionSongsFragmentToSongFragment2(it.id, it.song_name, it.song, it.song_text)
            findNavController().navigate(action)
        }
    }

    private fun setupObserver() {
        viewModel.songs.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {}
                ResourceState.SUCCESS -> {
                    adapter.models = it.data!!
                }
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
            }
        }
    }
}