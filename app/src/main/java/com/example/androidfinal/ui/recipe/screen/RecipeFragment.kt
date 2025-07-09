package com.example.androidfinal.ui.recipe.screen

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.databinding.FragmentRecipeBinding
import com.example.androidfinal.ui.recipe.vm.RecipeViewModel
import kotlinx.coroutines.launch

class RecipeFragment : BaseFragment<FragmentRecipeBinding>(FragmentRecipeBinding::inflate) {

    private val vm: RecipeViewModel by viewModels()
    private val args: RecipeFragmentArgs by navArgs()

    override fun bindViewActionListener() {
        val id = args.id
        vm.loadRecipesByTag(id)
    }

    override fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.recipe.collect { recipe ->
                    // TODO(): Use data for recipe
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
