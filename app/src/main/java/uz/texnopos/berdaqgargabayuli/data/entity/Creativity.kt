package uz.texnopos.berdaqgargabayuli.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Creativity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "info")
    val info: String
)