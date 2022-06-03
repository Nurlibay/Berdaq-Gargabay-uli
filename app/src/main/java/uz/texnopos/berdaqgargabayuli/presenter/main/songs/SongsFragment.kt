package uz.texnopos.berdaqgargabayuli.presenter.main.songs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.databinding.FragmentSongsBinding

class SongsFragment : Fragment(R.layout.fragment_songs) {

    private lateinit var binding: FragmentSongsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongsBinding.bind(view)
    }

}