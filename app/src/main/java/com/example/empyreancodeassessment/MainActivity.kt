package com.example.empyreancodeassessment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.empyreancodeassessment.features.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                loginFragment,
                LoginFragment.TAG
            )
            .commit()
    }
}