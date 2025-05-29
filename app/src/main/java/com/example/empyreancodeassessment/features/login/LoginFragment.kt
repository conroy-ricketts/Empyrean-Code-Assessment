package com.example.empyreancodeassessment.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.empyreancodeassessment.R

class LoginFragment : Fragment() {
    private lateinit var linearLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        linearLayout = view.findViewById(R.id.root_login_container)

        return view
    }

    companion object {
        const val TAG = "LoginFragment"
    }
}