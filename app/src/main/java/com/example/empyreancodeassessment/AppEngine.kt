package com.example.empyreancodeassessment

import com.example.empyreancodeassessment.network.models.ItemResponse
import com.example.empyreancodeassessment.network.models.UserResponse

class AppEngine {
    var currentUser: UserResponse? = null
    var authToken: String? = null
    var currentItem: ItemResponse? = null

    companion object {
        private var instance: AppEngine? = null

        fun getInstance(): AppEngine {
            return instance ?: AppEngine().also { instance = it }
        }
    }
}