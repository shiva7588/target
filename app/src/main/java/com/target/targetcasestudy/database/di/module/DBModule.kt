package com.target.targetcasestudy.database.di.module

import android.app.Application
import androidx.room.Room
import com.target.targetcasestudy.database.TargetDB
import com.target.targetcasestudy.database.dao.DealsDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {
    @Provides
    @Singleton
    internal fun providesDatabase(application: Application): TargetDB {
        return Room.databaseBuilder(
            application,
            TargetDB::class.java,
            "target.db"
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideDealsDao(appDatabase: TargetDB): DealsDAO {
        return appDatabase.dealsDao()
    }
}