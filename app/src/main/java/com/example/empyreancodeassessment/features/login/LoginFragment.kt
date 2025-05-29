package com.example.empyreancodeassessment.features.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.empyreancodeassessment.AppEngine
import com.example.empyreancodeassessment.ECAApplication
import com.example.empyreancodeassessment.R
import com.example.empyreancodeassessment.ViewModelFactory
import javax.inject.Inject

class LoginFragment : Fragment() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var loginNavigator: LoginNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<LoginViewModel>
    private val viewModel: LoginViewModel by lazy {
        viewModelFactory.get<LoginViewModel>(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ECAApplication.getAppComponent().inject(this)
        subscribeToViewModel()
        loginNavigator = requireActivity() as LoginNavigator
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        usernameEditText = view.findViewById(R.id.login_username_edit_text)
        passwordEditText = view.findViewById(R.id.login_password_edit_text)
        submitButton = view.findViewById(R.id.login_submit_button)

        submitButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            viewModel.loginUser(username, password)
        }

        return view
    }

    private fun subscribeToViewModel() {
        viewModel.loginSuccess.observe(this) { loginResponse ->
            Log.d("LoginFragment", "The user was successfully logged in!")

            AppEngine.getInstance().authToken = "Bearer " + loginResponse.token
            AppEngine.getInstance().currentUser = loginResponse.user

            loginNavigator.navigateToFeed()
        }

        viewModel.loginError.observe(this) { errorMessage ->
            Log.e("LoginFragment", "There was an error authenticating the user!: $errorMessage")

            Toast.makeText(
                requireContext(),
                "There was an error logging in. Please try again.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        const val TAG = "LoginFragment"
    }
}