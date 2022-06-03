package uz.texnopos.berdaqgargabayuli.presenter.main.lifestyle.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.databinding.FragmentLifestyleInfoBinding
import uz.texnopos.berdaqgargabayuli.utils.ResourceState
import uz.texnopos.berdaqgargabayuli.utils.showMessage

class LifeStyleInfoFragment : Fragment(R.layout.fragment_lifestyle_info) {

    private lateinit var binding: FragmentLifestyleInfoBinding
    private val args: LifeStyleInfoFragmentArgs by navArgs()
    private val viewModel: LifeStyleInfoViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLifestyleInfoBinding.bind(view).apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        viewModel.getLifeData(args.id)
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.lifeStyleInfo.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {}
                ResourceState.SUCCESS -> {
                    binding.tvLifeInfo.text = it.data!!.info
                    binding.toolbar.title = it.data.chapter
                }
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
            }
        }
    }
}