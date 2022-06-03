package uz.texnopos.berdaqgargabayuli.data.dao

import androidx.room.Dao
import androidx.room.Query
import uz.texnopos.berdaqgargabayuli.data.entity.Creativity
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle
import uz.texnopos.berdaqgargabayuli.data.entity.Poems
import uz.texnopos.berdaqgargabayuli.data.entity.Songs

@Dao
interface PoetDao {

    @Query("SELECT * FROM lifestyle")
    suspend fun getLifeData(): List<LifeStyle>

    @Query("SELECT * FROM lifestyle WHERE id=:id")
    suspend fun getLifeAgeInfo(id: Int): LifeStyle

    @Query("SELECT * FROM creativity")
    suspend fun getCreativityInfo(): Creativity

    @Query("SELECT * FROM songs")
    suspend fun getSongs(): List<Songs>

    @Query("SELECT * FROM songs WHERE id=:id")
    suspend fun getSongById(id: Int): Songs

    @Query("SELECT * FROM poems")
    suspend fun getPoems(): List<Poems>

    @Query("SELECT * FROM poems WHERE id=:id")
    suspend fun getPoemById(id: Int): Poems
}