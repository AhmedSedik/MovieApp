package com.example.myapplication.ui.view_all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentViewallBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * This @MovieFragment will hold all movies list ( popular, InTheater, Upcoming etc,..)
 *
 */
@AndroidEntryPoint
class ViewAllFragment : Fragment() {

    private lateinit var binding:FragmentViewallBinding
    private val args: ViewAllFragmentArgs by navArgs()

    private val viewAllViewModel: ViewAllViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewallBinding.inflate(
            inflater,
            container, false

        ).apply {
           viewModel = viewAllViewModel
            lifecycleOwner = this@ViewAllFragment.viewLifecycleOwner

        }

        return binding.root
    }


}