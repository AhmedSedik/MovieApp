package com.example.myapplication.ui.discover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDiscoverBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [DiscoverFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DiscoverFragment : Fragment() {

    private lateinit var binding: FragmentDiscoverBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)

        return binding.root
    }


}