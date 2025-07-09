package com.example.androidfinal.ui.categories.indian.screen

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.R
import com.example.androidfinal.databinding.FragmentIndianBinding
import com.example.androidfinal.ui.adapter.MenuAdapter
import com.example.androidfinal.ui.categories.indian.vm.IndianViewModel
import kotlinx.coroutines.launch

class IndianFragment : BaseFragment<FragmentIndianBinding>(FragmentIndianBinding::inflate) {

    private val vm: IndianViewModel by viewModels()
    private lateinit var menuAdapter: MenuAdapter

    override fun bindViewActionListener() {
        menuAdapter = MenuAdapter()
        with(binding) {
            indianRecipes.adapter = menuAdapter
            backArrow.setOnClickListener {
                findNavController().navigate(R.id.action_spicy_to_menu)
            }
        }
    }

    override fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.indianRecipes.collect {
                    menuAdapter.submitList(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.errorMessage.collect {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
