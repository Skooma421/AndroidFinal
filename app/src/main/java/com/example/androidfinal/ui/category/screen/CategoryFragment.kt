package com.example.androidfinal.ui.category.screen

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.R
import com.example.androidfinal.databinding.FragmentCategoryBinding
import com.example.androidfinal.ui.adapter.MenuAdapter
import com.example.androidfinal.ui.category.vm.CategoryVm
import kotlinx.coroutines.launch

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val vm: CategoryVm by viewModels()
    private lateinit var menuAdapter: MenuAdapter
    private val args: CategoryFragmentArgs by navArgs()

    override fun bindViewActionListener() {
        val tag = args.tag
        vm.loadRecipesByTag(tag)
        menuAdapter = MenuAdapter()
        with(binding) {
            categoryRecycler.adapter = menuAdapter
            titleTv.text = tag
            backArrow.setOnClickListener {
                findNavController().navigate(R.id.action_category_to_home)
            }
        }
        menuAdapter.setOnItemClickListener {
            val action = CategoryFragmentDirections.actionCategoryFragmentToRecipeFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.recipes.collect {
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
