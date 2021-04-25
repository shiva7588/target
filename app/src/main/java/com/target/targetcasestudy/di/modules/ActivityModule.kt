package com.target.targetcasestudy.di.modules

import com.target.targetcasestudy.DealsListFragment
import com.target.targetcasestudy.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}