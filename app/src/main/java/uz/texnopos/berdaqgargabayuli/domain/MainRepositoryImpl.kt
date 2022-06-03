package uz.texnopos.berdaqgargabayuli.domain

import uz.texnopos.berdaqgargabayuli.data.dao.PoetDao
import uz.texnopos.berdaqgargabayuli.data.entity.Creativity
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle

class MainRepositoryImpl constructor(private val dao: PoetDao) : MainRepository {

    override suspend fun getLifeData(): List<LifeStyle> {
        return dao.getLifeData()
    }

    override suspend fun getLifeAgeInfo(id: Int): LifeStyle {
        return dao.getLifeAgeInfo(id)
    }

    override suspend fun getCreativityInfo(): Creativity {
        return dao.getCreativityInfo()
    }

}