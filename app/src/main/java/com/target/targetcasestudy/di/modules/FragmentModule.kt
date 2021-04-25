package com.target.targetcasestudy.di.modules

import com.target.targetcasestudy.DealsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeDealsFragment(): DealsListFragment
}