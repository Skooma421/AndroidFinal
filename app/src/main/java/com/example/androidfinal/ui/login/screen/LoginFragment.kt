package com.example.androidfinal.ui.login.screen

import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.R
import com.example.androidfinal.ui.login.vm.LoginViewModel
import com.example.androidfinal.databinding.FragmentLoginBinding
import com.example.androidfinal.utils.SessionManager

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sessionManager: SessionManager

    override fun bindViewActionListener() {
        sessionManager = SessionManager(requireContext())

        binding.apply {
            eyeIcon0.setOnClickListener {
                passwordField.transformationMethod =
                    if (passwordField.transformationMethod == null)
                        PasswordTransformationMethod.getInstance()
                    else null

                eyeIcon0.setImageResource(
                    if (passwordField.transformationMethod == null) R.drawable.closed_eye_icon else R.drawable.eye_icon
                )
                passwordField.setSelection(passwordField.text?.length ?: 0)
            }

            loginButton.setOnClickListener {
                val email = emailField.text.toString().trim()
                val password = passwordField.text.toString().trim()
                viewModel.logic(email, password, sessionManager)
            }

            registerButton.setOnClickListener{
                findNavController().navigate(R.id.action_login_to_register)
            }
        }
    }

    override fun bindObservers() {
        viewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            if (success == true) {
                showMessage("Login successful")
                findNavController().navigate(R.id.action_login_to_home)
            }

        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                showMessage(it)
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}