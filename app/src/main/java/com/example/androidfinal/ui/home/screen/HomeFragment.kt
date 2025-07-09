package com.example.androidfinal.ui.home.screen

import androidx.fragment.app.viewModels
import com.example.androidfinal.BaseFragment
import com.example.androidfinal.databinding.FragmentHomeBinding
import com.example.androidfinal.ui.home.vm.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val vm: HomeViewModel by viewModels()

    override fun bindViewActionListener() {

    }

    override fun bindObservers() {

    }
}