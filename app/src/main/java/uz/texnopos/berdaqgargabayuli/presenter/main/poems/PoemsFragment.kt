package uz.texnopos.berdaqgargabayuli.presenter.main.poems

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.databinding.FragmentPoemsBinding

class PoemsFragment : Fragment(R.layout.fragment_poems) {

    private lateinit var binding: FragmentPoemsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPoemsBinding.bind(view)
    }

}