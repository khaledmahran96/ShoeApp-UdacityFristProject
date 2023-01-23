package com.udacity.shoestore.kotlinCode.shoeDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding.detailShoe = viewModel
        binding.setLifecycleOwner(this)




        viewModel.cancelButtonPressed.observe(viewLifecycleOwner , Observer {
            if (it){
                navToShoeListScreen()
                viewModel.onCancelButtonPressed()
            }
        })




        viewModel.saveButtonPressed.observe(viewLifecycleOwner , Observer {
            if (it){
                if(viewModel.isNotEmpty()){
                    val shoe = viewModel.makeNewShoe()
                    viewModel.addNewShoe(shoe)
                    viewModel.onSaveButtonPressed()
                    navToShoeListScreen()
                }
                else{
                    Toast.makeText(context , "Please check missing fields!!", Toast.LENGTH_SHORT).show()
                }
            }
        })




        return binding.root
    }

    private fun navToShoeListScreen() {
        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
    }

}