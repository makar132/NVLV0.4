package com.example.nvlv04.ui.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaRecorder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentSignupFourthBinding
import com.example.nvlv04.model.PrefManager
import com.example.nvlv04.ui.adapter.FamilyMemberRecyclerView
import com.example.nvlv04.ui.adapter.UserRecyclerView
import kotlinx.android.synthetic.main.fragment_signup_fourth.*
import com.example.nvlv04.model.entity.familyMember
import kotlinx.android.synthetic.main.app_report_reportfound_post.*
import kotlinx.android.synthetic.main.fragment_add_family_member_dialog.*
import kotlinx.android.synthetic.main.fragment_add_family_member_dialog.btn_upload_photo
import kotlinx.android.synthetic.main.fragment_add_family_member_dialog.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class SignupFragment_fourth : Fragment() {
    companion object {
        fun newInstance() = SignupFragment_fourth()
    }

var familyMemberList: ArrayList<familyMember> = ArrayList()

    var familyRecyclerView= FamilyMemberRecyclerView()
    val args: SignupFragment_fourthArgs by navArgs()
    private lateinit var binding: FragmentSignupFourthBinding
    private lateinit var prefManager: PrefManager
    lateinit var pic: AppCompatImageView
    lateinit var post_dialog:AlertDialog

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
                //findNavController().navigate(R.id.action_signupFragment_fourth_to_add_family_member_dialogFragment)
                val Dialog=LayoutInflater.from(context).inflate(R.layout.fragment_add_family_member_dialog,null)
                val dialogBuilder= AlertDialog.Builder(context)
                    .setView(Dialog)
                    .setTitle("Add Family Member")
                    .setPositiveButton("Add Member"){dialog, which ->
                        Toast.makeText(context, "add", Toast.LENGTH_SHORT).show()
                        val firstName=Dialog.et_first_name.text.toString()
                        val lastName=Dialog.et_last_name.text.toString()
                        val medicalcondition=Dialog.et_medical_condition.text.toString()

                        if(firstName.isEmpty()||lastName.isEmpty()){
                            Toast.makeText(context,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            familyMemberList.add(familyMember(Dialog.iv_member_image,firstName,lastName,medicalcondition))
                            familyRecyclerView.setList(familyMemberList)
                        }

                    }
                    .setNegativeButton("Cancel"){dialog, which ->
                        Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                val alertDialog=dialogBuilder.create()
                alertDialog.setOnShowListener {
                    pic=alertDialog.iv_member_image
                    post_dialog=alertDialog
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=false
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isClickable=false
                    alertDialog.et_first_name.addTextChangedListener{
                        if(it.toString().isNotEmpty()&&it.toString().isNotBlank() && alertDialog.et_last_name.text.isNotEmpty()&&alertDialog.et_last_name.text.isNotBlank()){
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=true
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isClickable=true
                        }
                        else{
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=false
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isClickable=false
                        }
                    }
                    alertDialog.et_last_name.addTextChangedListener{
                        if(it.toString().isNotEmpty()&&it.toString().isNotBlank() && alertDialog.et_first_name.text.isNotEmpty()&&alertDialog.et_first_name.text.isNotBlank()){
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=true
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isClickable=true
                        }
                        else{
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=false
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isClickable=false
                        }
                    }
                    alertDialog.btn_upload_photo.setOnClickListener {
                        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        GlobalScope.async {
                            startActivityForResult(intent, MediaRecorder.VideoSource.CAMERA)
                            onActivityResult(
                                Activity.RESULT_OK,
                                MediaRecorder.VideoSource.CAMERA,intent)
                        }
                    }

                }
                alertDialog.show()

            }
            else{
                Toast.makeText(requireContext(),"You can add only 3 family members",Toast.LENGTH_LONG).show()
            }
        }
        //familyRecyclerView.family+=familyMember(0,args.toBundle().getString("name")!!,args.toBundle().getString("medical_record")!!)
        binding.btnNext.setOnClickListener { findNavController().navigate(R.id.action_signupFragment_fourth_to_app_mainFragment) }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== MediaRecorder.VideoSource.CAMERA){
            if(resultCode==Activity.RESULT_OK){
                Toast.makeText(context,"Photo Taken",Toast.LENGTH_LONG).show()
                pic.setImageBitmap((data?.extras?.get("data") as Bitmap))
                if(pic.drawable!=null && post_dialog.et_first_name.text.toString().isNotEmpty()&&post_dialog.et_first_name.text.toString().isNotBlank()){
                    post_dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=true
                    post_dialog.getButton(AlertDialog.BUTTON_POSITIVE).isClickable=true
                }

            }
            else{
                Toast.makeText(context,"Photo Not Taken",Toast.LENGTH_LONG).show()
            }
        }
    }


}