package com.example.androidfinal.ui.recipe.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.R
import com.example.androidfinal.databinding.FragmentRecipeBinding
import com.example.androidfinal.ui.recipe.vm.RecipeViewModel
import kotlinx.coroutines.launch

class RecipeFragment : BaseFragment<FragmentRecipeBinding>(FragmentRecipeBinding::inflate) {

    private val vm: RecipeViewModel by viewModels()
    private val args: RecipeFragmentArgs by navArgs()

    override fun bindViewActionListener() {
        val id = args.id
        vm.loadRecipesByTag(id)
        binding.ivBackArrow.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment, inclusive = false)
        }
    }


    @SuppressLint("SetTextI18n")
    override fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.recipe.collect { recipe ->
                    if (recipe != null) {
                        binding.recipeName.text = recipe.name ?: "Unknown recipe"
                        binding.category.text =
                            "Category: ${recipe.tags?.joinToString(",") ?: "None"}"
                        binding.area.text = "Cuisine: ${recipe.cuisine ?: "Unknown"}"
                        binding.recipeInstructions.text =
                            recipe.instructions?.joinToString("\n") ?: "No instructions available"
                        Glide.with(this@RecipeFragment)
                            .load(recipe.image)
                            .centerCrop()
                            .placeholder(android.R.drawable.ic_menu_gallery)
                            .error(android.R.drawable.ic_menu_report_image)
                            .into(binding.imgMealDetail)
                    } else {
                        binding.recipeName.text = "Loading. . . "
                        binding.category.text = "Category: Loading. . . "
                        binding.area.text = "Cuisine: Loading. . . "
                        binding.recipeInstructions.text = "Instructions: Loading. . . "
                    }
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
