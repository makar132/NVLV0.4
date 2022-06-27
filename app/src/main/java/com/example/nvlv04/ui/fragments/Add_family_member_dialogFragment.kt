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
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            if(prefManager.getnavparent()=="home"){
                prefManager.setnavparent(null)
                findNavController().navigate(R.id.action_add_family_member_dialogFragment_to_app_mainFragment)
            }
            else {
                activity?.onBackPressed()
            }
        }

        prefManager = PrefManager(requireContext())
        binding.btnAddFamilyMemeber.setOnClickListener {
            val firstname=binding.etFirstName.text.toString()
            val lastname=binding.etLastName.text.toString()
            val membermedicalrecord=binding.etMedicalCondition.text.toString()
            if(firstname.isNotEmpty() && lastname.isNotEmpty() ){
                //val action = Add_family_member_dialogFragmentDirections.actionAddFamilyMemberDialogFragmentToSignupFragmentFourth(firstname,lastname,membermedicalrecord,0)
               prefManager.setFamilyMemberfirstname(firstname)
                prefManager.setFamilyMemberlastname(lastname)
                if(membermedicalrecord.isNotEmpty())prefManager.setFamilyMembermedicalrecord(membermedicalrecord)
                prefManager.setFamilyMemberimageid(0)
                    //activity?.onBackPressed()
                if(prefManager.getnavparent()=="home"){
                    prefManager.setnavparent(null)
                    findNavController().navigate(R.id.action_add_family_member_dialogFragment_to_app_mainFragment)
                }
                else {
                    findNavController().navigate(R.id.action_add_family_member_dialogFragment_to_signupFragment_fourth)
                }

            }

        }
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