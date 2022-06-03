package uz.texnopos.berdaqgargabayuli.presenter.main.creativity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.databinding.FragmentCreativityBinding
import uz.texnopos.berdaqgargabayuli.utils.ResourceState
import uz.texnopos.berdaqgargabayuli.utils.showMessage

class CreativityFragment : Fragment(R.layout.fragment_creativity) {

    private lateinit var binding: FragmentCreativityBinding
    private val viewModel: CreativityViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreativityBinding.bind(view).apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        viewModel.getCreativityInfo()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.creativityInfo.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {}
                ResourceState.SUCCESS -> {
                    binding.tvCreativityInfo.text = it.data!!.info
                }
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
            }
        }
    }

}