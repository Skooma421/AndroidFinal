package com.example.androidfinal.UI.categories.spicy.screen

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.R
import com.example.androidfinal.UI.adapter.RecipeAdapter
import com.example.androidfinal.UI.categories.spicy.vm.SpicyViewModel
import com.example.androidfinal.databinding.FragmentSpicyBinding


class SpicyFragment : BaseFragment<FragmentSpicyBinding>(FragmentSpicyBinding::inflate) {

    private val viewModel: SpicyViewModel by viewModels()
    private lateinit var adapter: RecipeAdapter

    override fun bindViewActionListener() {
        adapter = RecipeAdapter(emptyList())
        binding.rvSpicyRecipes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSpicyRecipes.adapter = adapter

        binding.backArrow.setOnClickListener{
            findNavController().navigate(R.id.action_spicy_to_menu)
        }
        viewModel.loadSpicyRecipes()
    }

     override fun bindObservers(){
        viewModel.spicyRecipes.observe(viewLifecycleOwner){ recipes ->
            adapter = RecipeAdapter(recipes)
            binding.rvSpicyRecipes.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner){ message ->
            Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
        }
    }
}