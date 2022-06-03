package uz.texnopos.berdaqgargabayuli.presenter.main.songs

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.data.entity.Songs
import uz.texnopos.berdaqgargabayuli.databinding.ItemLifestyleBinding
import uz.texnopos.berdaqgargabayuli.utils.inflate
import uz.texnopos.berdaqgargabayuli.utils.onClick

class SongListAdapter : RecyclerView.Adapter<SongListAdapter.SongsViewHolder>() {

    var models: List<Songs> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onClickItem: (songs: Songs) -> Unit = {}

    fun setOnClickItem(onClickItem: (songs: Songs) -> Unit) {
        this.onClickItem = onClickItem
    }

    inner class SongsViewHolder(private val binding: ItemLifestyleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun populateModel(songs: Songs) {
            binding.apply {
                tvLifeAge.text = songs.song_name
                itemAgeStatus.onClick {
                    onClickItem.invoke(songs)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val itemView = parent.inflate(R.layout.item_lifestyle)
        val binding = ItemLifestyleBinding.bind(itemView)
        return SongsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size
}