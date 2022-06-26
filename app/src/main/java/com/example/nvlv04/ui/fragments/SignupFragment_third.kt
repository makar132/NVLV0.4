package com.example.nvlv04.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentSignupThirdBinding

class SignupFragment_third : Fragment() {

    companion object {
        fun newInstance() = SignupFragment_third()
    }

    private lateinit var viewModel: SignupFragmentThirdViewModel
    lateinit var binding: FragmentSignupThirdBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_third_to_signupFragment_fourth)
        }

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupFragmentThirdViewModel::class.java)
        // TODO: Use the ViewModel
    }



}