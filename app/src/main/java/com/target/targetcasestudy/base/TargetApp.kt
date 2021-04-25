package com.target.targetcasestudy.base

import android.app.Activity
import android.app.Application
import com.target.targetcasestudy.database.di.module.DBModule
import com.target.targetcasestudy.di.component.DaggerAppComponent
import com.target.targetcasestudy.webapi.di.module.WebApiModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TargetApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .dbModule(DBModule())
            .webApiModule(WebApiModule())
            .build()
            .inject(this)
    }
}