package com.example.empyreancodeassessment

import android.app.Application
import com.example.empyreancodeassessment.dagger.AppComponent
import com.example.empyreancodeassessment.dagger.AppModule
import com.example.empyreancodeassessment.dagger.DaggerAppComponent

class ECAApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDaggerAppComponent()
    }

    private fun initDaggerAppComponent(): AppComponent {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

        return appComponent
    }

    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent(): AppComponent = appComponent
    }
}