package com.example.nvlv04.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentAddFamilyMemberDialogBinding
import com.example.nvlv04.model.PrefManager

class Add_family_member_dialogFragment : Fragment(){
lateinit var binding: FragmentAddFamilyMemberDialogBinding
    companion object {


        fun newInstance() = Add_family_member_dialogFragment()
    }

    private lateinit var viewModel: AddFamilyMemberDialogViewModel
    lateinit var prefManager: PrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFamilyMemberDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddFamilyMemberDialogViewModel::class.java]
        // TODO: Use the ViewModel
    }
    /*override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = 1000
        params.height = 1550
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }*/

}