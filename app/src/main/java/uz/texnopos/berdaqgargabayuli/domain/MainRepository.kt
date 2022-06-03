package uz.texnopos.berdaqgargabayuli.domain

import uz.texnopos.berdaqgargabayuli.data.entity.Creativity
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle
import uz.texnopos.berdaqgargabayuli.data.entity.Poems
import uz.texnopos.berdaqgargabayuli.data.entity.Songs

interface MainRepository {

    suspend fun getLifeData(): List<LifeStyle>

    suspend fun getSongs(): List<Songs>

    suspend fun getLifeAgeInfo(id: Int): LifeStyle

    suspend fun getCreativityInfo(): Creativity

    suspend fun getSongById(id: Int): Songs

    suspend fun getPoems(): List<Poems>

    suspend fun getPoemById(id: Int): Poems

}