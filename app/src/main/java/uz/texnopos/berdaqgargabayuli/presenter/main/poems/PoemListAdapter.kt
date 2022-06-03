package uz.texnopos.berdaqgargabayuli.presenter.main.poems

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.data.entity.Poems
import uz.texnopos.berdaqgargabayuli.data.entity.Songs
import uz.texnopos.berdaqgargabayuli.databinding.ItemLifestyleBinding
import uz.texnopos.berdaqgargabayuli.utils.inflate
import uz.texnopos.berdaqgargabayuli.utils.onClick

class PoemListAdapter : RecyclerView.Adapter<PoemListAdapter.PoemsViewHolder>() {

    var models: List<Poems> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onClickItem: (poem: Poems) -> Unit = {}

    fun setOnClickItem(onClickItem: (poem: Poems) -> Unit) {
        this.onClickItem = onClickItem
    }

    inner class PoemsViewHolder(private val binding: ItemLifestyleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun populateModel(poem: Poems) {
            binding.apply {
                tvLifeAge.text = poem.poem
                itemAgeStatus.onClick {
                    onClickItem.invoke(poem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemsViewHolder {
        val itemView = parent.inflate(R.layout.item_lifestyle)
        val binding = ItemLifestyleBinding.bind(itemView)
        return PoemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PoemsViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size
}