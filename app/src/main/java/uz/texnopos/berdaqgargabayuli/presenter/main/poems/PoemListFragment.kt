package uz.texnopos.berdaqgargabayuli.presenter.main.poems

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.databinding.FragmentPoemsBinding
import uz.texnopos.berdaqgargabayuli.utils.ResourceState
import uz.texnopos.berdaqgargabayuli.utils.showMessage

class PoemListFragment : Fragment(R.layout.fragment_poems) {

    private lateinit var binding: FragmentPoemsBinding
    private val viewModel: PoemListViewModel by viewModel()
    private val adapter = PoemListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPoemsBinding.bind(view).apply {
            rvPoems.adapter = adapter
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        viewModel.getPoems()
        setupObserver()
        adapter.setOnClickItem {
            val action = PoemListFragmentDirections.actionPoemListFragment3ToPoemFragment(it.id, it.poem, it.poem_audio?:"", it.poem_text)
            findNavController().navigate(action)
        }
    }

    private fun setupObserver() {
        viewModel.poem.observe(viewLifecycleOwner) {
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