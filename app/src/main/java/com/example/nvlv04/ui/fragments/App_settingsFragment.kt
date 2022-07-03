package com.example.nvlv04.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentAppSettingsBinding
import com.example.nvlv04.model.PrefManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class App_settingsFragment : Fragment() {
    lateinit var prefManager: PrefManager
    lateinit var binding: FragmentAppSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAppSettingsBinding.inflate(inflater, container, false)
        prefManager= PrefManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogOut.setOnClickListener {
            prefManager.removeData()
            findNavController().navigate(R.id.action_app_mainFragment_to_signinFragment)
        }
        binding.btnChangeApperance.setOnClickListener {
            Toast.makeText(context, "coming soon" , Toast.LENGTH_SHORT).show()
        }
        binding.btnChangePassword.setOnClickListener {
            Toast.makeText(context, "coming soon" , Toast.LENGTH_SHORT).show()
        }
        binding.btnShowFamilyQr.setOnClickListener {
            Toast.makeText(context, "coming soon" , Toast.LENGTH_SHORT).show()
        }
    }

}