package uz.texnopos.berdaqgargabayuli.domain

import uz.texnopos.berdaqgargabayuli.data.dao.PoetDao
import uz.texnopos.berdaqgargabayuli.data.entity.Creativity
import uz.texnopos.berdaqgargabayuli.data.entity.LifeStyle
import uz.texnopos.berdaqgargabayuli.data.entity.Poems
import uz.texnopos.berdaqgargabayuli.data.entity.Songs

class MainRepositoryImpl constructor(private val dao: PoetDao) : MainRepository {

    override suspend fun getLifeData(): List<LifeStyle> {
        return dao.getLifeData()
    }

    override suspend fun getSongs(): List<Songs> {
        return dao.getSongs()
    }

    override suspend fun getLifeAgeInfo(id: Int): LifeStyle {
        return dao.getLifeAgeInfo(id)
    }

    override suspend fun getCreativityInfo(): Creativity {
        return dao.getCreativityInfo()
    }

    override suspend fun getSongById(id: Int): Songs {
        return dao.getSongById(id)
    }

    override suspend fun getPoems(): List<Poems> {
        return dao.getPoems()
    }

    override suspend fun getPoemById(id: Int): Poems {
        return dao.getPoemById(id)
    }


}