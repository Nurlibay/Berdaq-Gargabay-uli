package uz.texnopos.berdaqgargabayuli.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.texnopos.berdaqgargabayuli.data.dao.PoetDao
import uz.texnopos.berdaqgargabayuli.data.entity.Creativity
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle
import uz.texnopos.berdaqgargabayuli.data.entity.Poems
import uz.texnopos.berdaqgargabayuli.data.entity.Songs

@Database(entities = [LifeStyle::class, Creativity::class, Songs::class, Poems::class], version = 5)
abstract class PoetDatabase : RoomDatabase() {

    companion object {
        private lateinit var INSTANCE: PoetDatabase
        fun getInstance(context: Context): PoetDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context, PoetDatabase::class.java,
                    "poet4.db"
                )
                    .createFromAsset("poet4.db")
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun dao(): PoetDao
}