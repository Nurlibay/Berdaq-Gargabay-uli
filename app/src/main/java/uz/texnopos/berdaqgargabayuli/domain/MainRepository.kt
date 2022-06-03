package uz.texnopos.berdaqgargabayuli.domain

import uz.texnopos.berdaqgargabayuli.data.entity.Creativity
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle

interface MainRepository {

    suspend fun getLifeData(): List<LifeStyle>

    suspend fun getLifeAgeInfo(id: Int): LifeStyle

    suspend fun getCreativityInfo(): Creativity

}