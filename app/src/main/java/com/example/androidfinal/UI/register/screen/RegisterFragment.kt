package com.example.androidfinal.UI.register.screen

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.R
import com.example.androidfinal.UI.register.vm.RegisterViewModel
import com.example.androidfinal.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels {
        object : ViewModelProvider.AndroidViewModelFactory(requireActivity().application) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewActionListener()
    }
//test2
    override fun bindViewActionListener() {
        binding.apply {
            emailField.addTextChangedListener { validateFields() }
            passwordField.addTextChangedListener { validateFields() }
            passwordRepeatField.addTextChangedListener { validateFields() }

            registerButton.setOnClickListener {
                val email = emailField.text.toString().trim()
                val password = passwordField.text.toString().trim()
                val passwordRepeat = passwordRepeatField.text.toString().trim()

                if (email.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty()) {
                    showMessage("Please fill in all fields")
                    return@setOnClickListener
                }

                if (!isValidPassword(password)) {
                    showMessage("Password must be at least 6 characters")
                    return@setOnClickListener
                }

                if (password != passwordRepeat) {
                    showMessage("Passwords do not match")
                    return@setOnClickListener
                }

                registerButton.isEnabled = false
                viewModel.register(email, password)
            }

            arrow.setOnClickListener {
                findNavController().navigate(R.id.action_register_to_login)
            }

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

            eyeIcon1.setOnClickListener {
                passwordRepeatField.transformationMethod =
                    if (passwordRepeatField.transformationMethod == null)
                        PasswordTransformationMethod.getInstance()
                    else null

                eyeIcon1.setImageResource(
                    if (passwordRepeatField.transformationMethod == null) R.drawable.closed_eye_icon else R.drawable.eye_icon
                )
                passwordRepeatField.setSelection(passwordRepeatField.text?.length ?: 0)
            }

            viewModel.registerSuccess.observe(viewLifecycleOwner) { success ->
                registerButton.isEnabled = true
                if (success == true) {
                    showMessage("Registration successful")
                    findNavController().navigate(R.id.action_register_to_login)
                }
            }

            viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
                if (!error.isNullOrEmpty()) {
                    registerButton.isEnabled = true
                    showMessage(error)
                }
            }
        }
    }

    private fun validateFields() {
        val email = binding.emailField.text?.toString()?.trim() ?: ""
        val password = binding.passwordField.text?.toString()?.trim() ?: ""
        val passwordRepeat = binding.passwordRepeatField.text?.toString()?.trim() ?: ""

        binding.registerButton.isEnabled =
            email.isNotEmpty() && password.isNotEmpty() && passwordRepeat.isNotEmpty()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}