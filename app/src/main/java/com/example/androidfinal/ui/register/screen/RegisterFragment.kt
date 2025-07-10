package com.example.androidfinal.ui.register.screen

import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.R
import com.example.androidfinal.ui.register.vm.RegisterViewModel
import com.example.androidfinal.databinding.FragmentRegisterBinding
import com.example.androidfinal.utils.SessionManager

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var sessionManager: SessionManager

    override fun bindViewActionListener() {

        sessionManager = SessionManager(requireContext())

        binding.apply {

            listOf(emailField, passwordField, passwordRepeatField).forEach { editText ->
                editText.addTextChangedListener { validateFields() }
            }

            registerButton.setOnClickListener {
                val email = emailField.text.toString().trim()
                val password = passwordField.text.toString().trim()
                val passwordRepeat = passwordRepeatField.text.toString().trim()

                val inputs = listOf(email, password, passwordRepeat)
                if (inputs.any { it.isBlank() }) {
                    showMessage("Please fill in all fields")
                    return@setOnClickListener
                }

                if (password != passwordRepeat) {
                    showMessage("Passwords do not match")
                    return@setOnClickListener
                }

                if (!isValidPassword(password)) {
                    showMessage("Password not strong enough")
                    return@setOnClickListener
                }

                registerButton.isEnabled = false
                viewModel.register(email, password) { emailSaved, passwordSaved ->
                    sessionManager.saveUser(emailSaved, passwordSaved)
                    sessionManager.setLoginStatus(true)
                }
            }
            loginButton.setOnClickListener{
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
        }
    }

    override fun bindObservers() {
        binding.apply {
            viewModel.registerSuccess.observe(viewLifecycleOwner) { success ->
                registerButton.isEnabled = true
                if (success == true) {
                    showMessage("Registration successful")
                    findNavController().navigate(R.id.action_register_to_login)
                }
                registerButton.isEnabled = validateFieldsState()
            }

            viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
                if (error != null && error.isEmpty()) {
                    registerButton.isEnabled = true
                    showMessage(error)
                }
            }
        }
    }

    private fun validateFields() {
        val fields = listOf(
            binding.emailField.text?.toString()?.trim(),
            binding.passwordField.text?.toString()?.trim(),
            binding.passwordRepeatField.text?.toString()?.trim()
        )
        binding.registerButton.isEnabled = fields.none {
            it.isNullOrEmpty()
        }
    }

    private fun validateFieldsState(): Boolean {
        val fields = listOf(
            binding.emailField.text?.toString()?.trim(),
            binding.passwordField.text?.toString()?.trim(),
            binding.passwordRepeatField.text?.toString()?.trim(),
        )
        return fields.none { it.isNullOrEmpty() }
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$".toRegex()
        return passwordPattern.matches(password)
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}