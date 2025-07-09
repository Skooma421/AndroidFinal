package com.example.androidfinal.ui.home.screen

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.databinding.FragmentHomeBinding
import com.example.androidfinal.ui.adapter.MenuAdapter
import com.example.androidfinal.ui.adapter.RecipeAdapter
import com.example.androidfinal.ui.adapter.TagAdapter
import com.example.androidfinal.ui.home.vm.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val vm: HomeViewModel by viewModels()
    private lateinit var tagAdapter: TagAdapter
    private lateinit var recipeAdapter: RecipeAdapter

    override fun bindViewActionListener() {
        tagAdapter = TagAdapter()
        recipeAdapter = RecipeAdapter()
        with(binding) {
            tagRecycler.adapter = tagAdapter
            recipeRecycler.adapter = recipeAdapter
        }
        tagAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeToCategory(it)
            findNavController().navigate(action)
        }
        recipeAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRecipeFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.tagList.collect {
                    tagAdapter.submitList(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.recipeList.collect {
                    recipeAdapter.submitList(it)
                }
            }
        }
    }
}