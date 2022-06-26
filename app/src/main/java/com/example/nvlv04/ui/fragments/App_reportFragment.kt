package com.example.nvlv04.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.budiyev.android.codescanner.CodeScanner
import com.example.nvlv04.databinding.FragmentAppReportBinding

class App_reportFragment : Fragment() {
    // TODO: Rename and change types of parameters
  lateinit var binding:FragmentAppReportBinding
  lateinit var codeScanner: CodeScanner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentAppReportBinding.inflate(inflater,container,false)




        return binding.root
    }



}