package uz.texnopos.berdaqgargabayuli.presenter.main.lifestyle

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.databinding.FragmentLifestyleBinding
import uz.texnopos.berdaqgargabayuli.utils.ResourceState
import uz.texnopos.berdaqgargabayuli.utils.showMessage

class LifeStyleFragment : Fragment(R.layout.fragment_lifestyle) {

    private lateinit var binding: FragmentLifestyleBinding
    private val viewModel: LifeStyleViewModel by viewModel()
    private val adapter = LifeStyleAdapter()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLifestyleBinding.bind(view).apply {
            rvLifeStyle.adapter = adapter
            navController = findNavController()
            toolbar.setNavigationOnClickListener  {
                navController.popBackStack()
            }
        }
        viewModel.getLifeData()
        setupObserver()
        adapter.setOnClickItem {
            navController.navigate(LifeStyleFragmentDirections.actionLifeStyleFragmentToLifeStyleInfoFragment(it.id))
        }
    }

    private fun setupObserver() {
        viewModel.lifeStyle.observe(viewLifecycleOwner) {
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