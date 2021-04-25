package com.target.targetcasestudy.di.component

import android.app.Application
import com.target.targetcasestudy.base.TargetApp
import com.target.targetcasestudy.database.di.module.DBModule
import com.target.targetcasestudy.di.modules.ActivityModule
import com.target.targetcasestudy.di.modules.FragmentModule
import com.target.targetcasestudy.di.modules.ViewModelModule
import com.target.targetcasestudy.webapi.di.module.WebApiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        DBModule::class,
        WebApiModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun dbModule(dbModule: DBModule): Builder

        @BindsInstance
        fun webApiModule(webApiModule: WebApiModule): Builder

        fun build(): AppComponent
    }

    fun inject(application: TargetApp)
}