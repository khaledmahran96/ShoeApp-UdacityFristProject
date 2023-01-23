package com.udacity.shoestore.kotlinCode.instruction

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
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    private lateinit var binding: FragmentInstructionBinding
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater ,
            R.layout.fragment_instruction ,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding.instruction = viewModel
        binding.setLifecycleOwner(this)

        viewModel.nextInstructionButtonPressed.observe(viewLifecycleOwner , Observer {
            if(it){
                navToShoeListScreen()
                viewModel.onNextInstructionButtonPressed()
            }
        })

        return binding.root
    }

    private fun navToShoeListScreen() {
        findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment())
    }


}