package com.example.nvlv04.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentStartBinding
import com.example.nvlv04.model.PrefManager

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    lateinit var prefManager: PrefManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater, container, false)
        prefManager = PrefManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(prefManager.Onboard() == true){
            if(prefManager.isLogin()==true){
                Handler(Looper.getMainLooper()).postDelayed({
                    findNavController().navigate(R.id.action_startFragment_to_app_mainFragment)
                }, 2000)
            }
            else{

            Handler(Looper.myLooper()!!).postDelayed({
                findNavController().navigate(R.id.action_startFragment_to_signinFragment)
            }, 2000)
            }

        }
        else{
         findNavController().navigate(R.id.action_startFragment_to_app_onboardingFragment)
        }


    }

}