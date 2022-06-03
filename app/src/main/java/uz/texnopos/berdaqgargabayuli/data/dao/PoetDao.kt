package uz.texnopos.berdaqgargabayuli.data.dao

import androidx.room.Dao
import androidx.room.Query
import uz.texnopos.berdaqgargabayuli.data.entity.Creativity
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle

@Dao
interface PoetDao {

    @Query("SELECT * FROM lifestyle")
    suspend fun getLifeData(): List<LifeStyle>

    @Query("SELECT * FROM lifestyle WHERE id=:id")
    suspend fun getLifeAgeInfo(id: Int): LifeStyle

    @Query("SELECT * FROM creativity")
    suspend fun getCreativityInfo(): Creativity

}