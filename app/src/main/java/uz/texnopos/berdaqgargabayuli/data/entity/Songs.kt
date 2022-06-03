package uz.texnopos.berdaqgargabayuli.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Songs(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var song_name: String,
    var song: String,
    var song_text: String
)