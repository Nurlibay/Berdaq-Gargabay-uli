package uz.texnopos.berdaqgargabayuli.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Poems(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var poem: String,
    var poem_audio: String? = null,
    var poem_text: String
)