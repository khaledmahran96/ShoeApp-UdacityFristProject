package com.udacity.shoestore.kotlinCode.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding.welcome = viewModel
        binding.setLifecycleOwner(this)

        viewModel.nextWelcomeButtonPressed.observe(viewLifecycleOwner , Observer {
            if(it){
                navToIntractionScreen()
                viewModel.onNextWelcomeButtonPressed()
            }
        })

     return binding.root
    }

    private fun navToIntractionScreen() {
        findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
    }

}