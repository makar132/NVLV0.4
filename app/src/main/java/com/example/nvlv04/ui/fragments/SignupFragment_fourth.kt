package com.example.nvlv04.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentSignupFourthBinding
import com.example.nvlv04.model.PrefManager
import com.example.nvlv04.ui.adapter.FamilyMemberRecyclerView
import com.example.nvlv04.ui.adapter.UserRecyclerView
import kotlinx.android.synthetic.main.fragment_signup_fourth.*
import com.example.nvlv04.model.entity.familyMember
class SignupFragment_fourth : Fragment() {
    companion object {
        fun newInstance() = SignupFragment_fourth()
    }

var familyMemberList: ArrayList<familyMember> = ArrayList()

    var familyRecyclerView= FamilyMemberRecyclerView()
    val args: SignupFragment_fourthArgs by navArgs()
    private lateinit var binding: FragmentSignupFourthBinding
    private lateinit var prefManager: PrefManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSignupFourthBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefManager=PrefManager(requireContext())
        binding.rvFamilyMembers.adapter=familyRecyclerView
        binding.btnAddFamilyMember.setOnClickListener{
            if(familyRecyclerView.itemCount<3) {
                findNavController().navigate(R.id.action_signupFragment_fourth_to_add_family_member_dialogFragment)
            }
            else{
                Toast.makeText(requireContext(),"You can add only 3 family members",Toast.LENGTH_LONG).show()
            }
        }
        //familyRecyclerView.family+=familyMember(0,args.toBundle().getString("name")!!,args.toBundle().getString("medical_record")!!)
        binding.btnNext.setOnClickListener { findNavController().navigate(R.id.action_signupFragment_fourth_to_app_mainFragment) }
    }


    override fun onStart() {
        super.onStart()

    }
    override fun onResume() {
        super.onResume()
        if(prefManager.getFamilyMemberfirstname()!=""){
            val firstname=prefManager.getFamilyMemberfirstname()
            val lastname=prefManager.getFamilyMemberlastname()
            val medicalrecord=prefManager.getFamilyMembermedicalrecord()
            val memberimageid=prefManager.getFamilyMemberimageid()
            if(firstname!=null && lastname!=null && memberimageid!=null){
                familyMemberList.add(familyMember(memberimageid,firstname,lastname,medicalrecord!!))
                familyRecyclerView.setList(familyMemberList)
            }
            prefManager.reserfamilyMember()

        }

    }

}