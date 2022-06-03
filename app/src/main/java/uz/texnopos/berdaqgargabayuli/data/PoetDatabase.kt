package uz.texnopos.berdaqgargabayuli.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.texnopos.berdaqgargabayuli.data.dao.PoetDao
import uz.texnopos.berdaqgargabayuli.data.entity.Creativity
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle

@Database(entities = [LifeStyle::class, Creativity::class], version = 2)
abstract class PoetDatabase : RoomDatabase() {

    companion object {
        private lateinit var INSTANCE: PoetDatabase
        fun getInstance(context: Context): PoetDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context, PoetDatabase::class.java,
                    "poet1.db"
                )
                    .createFromAsset("poet1.db")
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun dao(): PoetDao
}