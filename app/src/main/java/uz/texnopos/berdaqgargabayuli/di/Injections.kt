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
}
