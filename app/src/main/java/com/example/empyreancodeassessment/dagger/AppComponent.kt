package com.example.empyreancodeassessment.dagger

import com.example.empyreancodeassessment.ECAApplication
import com.example.empyreancodeassessment.features.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: ECAApplication)
    fun inject(loginFragment: LoginFragment)
}