package uz.texnopos.berdaqgargabayuli.presenter.main.lifestyle

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle
import uz.texnopos.berdaqgargabayuli.databinding.ItemLifestyleBinding
import uz.texnopos.berdaqgargabayuli.utils.inflate
import uz.texnopos.berdaqgargabayuli.utils.onClick

class LifeStyleAdapter : RecyclerView.Adapter<LifeStyleAdapter.LifeStyleViewHolder>() {

    var models: List<LifeStyle> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onClickItem: (lifestyle: LifeStyle) -> Unit = {}

    fun setOnClickItem(onClickItem: (lifestyle: LifeStyle) -> Unit) {
        this.onClickItem = onClickItem
    }

    inner class LifeStyleViewHolder(private val binding: ItemLifestyleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun populateModel(lifestyle: LifeStyle) {
            binding.apply {
                tvLifeAge.text = lifestyle.chapter
                itemAgeStatus.onClick {
                    onClickItem.invoke(lifestyle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LifeStyleViewHolder {
        val itemView = parent.inflate(R.layout.item_lifestyle)
        val binding = ItemLifestyleBinding.bind(itemView)
        return LifeStyleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LifeStyleViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size
}