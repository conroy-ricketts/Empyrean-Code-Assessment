package com.example.empyreancodeassessment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.empyreancodeassessment.features.feed.FeedFragment
import com.example.empyreancodeassessment.features.feed.FeedItemNavigator
import com.example.empyreancodeassessment.features.itemDetail.ItemDetailFragment
import com.example.empyreancodeassessment.features.login.LoginFragment
import com.example.empyreancodeassessment.features.login.LoginNavigator

class MainActivity : AppCompatActivity(), LoginNavigator, FeedItemNavigator {
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

    override fun navigateToFeed() {
        val feedFragment = FeedFragment()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                feedFragment,
                FeedFragment.TAG
            )
            .addToBackStack(LoginFragment.TAG)
            .commit()
    }

    override fun navigateToItemDetail() {
        val itemDetailFragment = ItemDetailFragment()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                itemDetailFragment,
                ItemDetailFragment.TAG
            )
            .addToBackStack(FeedFragment.TAG)
            .commit()
    }
}