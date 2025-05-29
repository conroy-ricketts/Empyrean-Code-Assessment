package com.example.empyreancodeassessment.dagger

import android.app.Application
import com.example.empyreancodeassessment.ECAApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: ECAApplication) {
    @Provides
    @Singleton
    fun getApplication(): Application = application
}