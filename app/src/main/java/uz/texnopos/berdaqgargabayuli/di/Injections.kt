package uz.texnopos.berdaqgargabayuli.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.texnopos.berdaqgargabayuli.data.PoetDatabase
import uz.texnopos.berdaqgargabayuli.domain.MainRepository
import uz.texnopos.berdaqgargabayuli.domain.MainRepositoryImpl
import uz.texnopos.berdaqgargabayuli.presenter.main.creativity.CreativityViewModel
import uz.texnopos.berdaqgargabayuli.presenter.main.lifestyle.LifeStyleViewModel
import uz.texnopos.berdaqgargabayuli.presenter.main.lifestyle.info.LifeStyleInfoViewModel
import uz.texnopos.berdaqgargabayuli.presenter.main.poems.PoemListViewModel
import uz.texnopos.berdaqgargabayuli.presenter.main.songs.SongListViewModel
import uz.texnopos.berdaqgargabayuli.presenter.main.poems.poem.PoemViewModel
import uz.texnopos.berdaqgargabayuli.presenter.main.songs.song.SongViewModel

val roomModule = module {
    single { PoetDatabase.getInstance(androidContext()).dao() }
}

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(dao = get()) }
}

val viewModelModule = module {
    viewModel { LifeStyleViewModel(mainRepository = get()) }
    viewModel { LifeStyleInfoViewModel(mainRepository = get()) }
    viewModel { CreativityViewModel(mainRepository = get()) }
    viewModel { SongListViewModel(mainRepository = get()) }
    viewModel { SongViewModel(mainRepository = get()) }
    viewModel { PoemListViewModel(mainRepository = get()) }
    viewModel { PoemViewModel(mainRepository = get()) }
}
