package com.example.nvlv04.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentSignupSecondBinding

class SignupFragment_second : Fragment() {
lateinit var binding: FragmentSignupSecondBinding
    companion object {
        fun newInstance() = SignupFragment_second()
    }

    private lateinit var viewModel: SignupFragmentSecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_second_to_signupFragment_third)
        }

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[SignupFragmentSecondViewModel::class.java]
        // TODO: Use the ViewModel
    }

    }