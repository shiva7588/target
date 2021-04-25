package com.target.targetcasestudy.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.annotations.ViewModelKey
import com.target.targetcasestudy.factory.ViewModelFactory
import com.target.targetcasestudy.ui.viewmodel.DealViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DealViewModel::class)
    protected abstract fun dealViewModel(dealViewModel: DealViewModel): ViewModel
}