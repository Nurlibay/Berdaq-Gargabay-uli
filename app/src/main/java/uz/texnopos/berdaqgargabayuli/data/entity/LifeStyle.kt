package uz.texnopos.berdaqgargabayuli.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LifeStyle(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "chapter")
    var chapter: String,
    @ColumnInfo(name = "info")
    var info: String,
)
