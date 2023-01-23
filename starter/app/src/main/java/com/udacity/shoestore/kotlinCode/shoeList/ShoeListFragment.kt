package com.udacity.shoestore.kotlinCode.shoeList

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeScrolviewBinding
import kotlinx.android.synthetic.main.shoe_scrolview.view.*


class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding.list = viewModel
        binding.setLifecycleOwner(this)

        setHasOptionsMenu(true)

        viewModel.floatingButtonPressed.observe(viewLifecycleOwner , Observer {
            if(it){
                navToShoeDetail()
                viewModel.onFloatingButtonPressed()
            }
        })

        viewModel.list.observe(viewLifecycleOwner, Observer { list ->
            for(shoe in list){
                val linyarLayout: ShoeScrolviewBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.shoe_scrolview,
                    container,
                    false
                )


                linyarLayout.shoeItemView = shoe
                var size = shoe.size.toString()
                linyarLayout.shoeSizeText.shoeSize_text.text = getString(R.string.shoeSize_quato , size)

                binding.shoeList.addView(linyarLayout.root)
            }
        })


        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().popBackStack()
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu , menu)
    }

    private fun navToShoeDetail() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
    }


}