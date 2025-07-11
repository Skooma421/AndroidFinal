package com.example.androidfinal.ui.userProfile.screen

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.R
import com.example.androidfinal.ui.userProfile.factory.ProfileViewModelFactory
import com.example.androidfinal.ui.userProfile.vm.ProfileViewModel
import com.example.androidfinal.databinding.FragmentProfileBinding
import com.example.androidfinal.utils.SessionManager

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: ProfileViewModel

    override fun bindViewActionListener() {
        sessionManager = SessionManager(requireContext())
        val factory = ProfileViewModelFactory(sessionManager)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        viewModel.loadUserMail()

        binding.logoutButton.setOnClickListener {
            viewModel.logout()
        }
    }

    override fun bindObservers() {
        viewModel.email.observe(viewLifecycleOwner) { email ->
            binding.userEmail.text = email ?: "Unknown user"
        }

        viewModel.loggedOut.observe(viewLifecycleOwner) { isLoggedOut ->
            if (isLoggedOut == true) {
                Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_profile_to_login)
            }
        }
    }
}