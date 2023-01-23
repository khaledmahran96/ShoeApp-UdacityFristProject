package com.udacity.shoestore.kotlinCode.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =DataBindingUtil.inflate(
            inflater ,
            R.layout.fragment_login ,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding.login = viewModel
        binding.setLifecycleOwner(this)

        viewModel.loginButtonPressed.observe(viewLifecycleOwner , Observer {
            if(it){
                navToWelcomeScreen()
                viewModel.onLoginButtonPressed()
            }
        } )




        return binding.root
    }

    private fun navToWelcomeScreen() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())

    }
}

