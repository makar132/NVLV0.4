package com.example.nvlv04.ui.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.media.MediaRecorder
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentAppHomeBinding
import com.example.nvlv04.model.PrefManager
import com.example.nvlv04.model.entity.familyMember
import com.example.nvlv04.ui.adapter.FamilyMemberRecyclerView
import com.example.nvlv04.ui.adapter.OnListItemClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_report_reportfound_post.*
import kotlinx.android.synthetic.main.fragment_add_family_member_dialog.*
import kotlinx.android.synthetic.main.fragment_add_family_member_dialog.btn_upload_photo
import kotlinx.android.synthetic.main.fragment_add_family_member_dialog.view.*
import kotlinx.android.synthetic.main.fragment_signup_fourth.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [App_homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class App_homeFragment : Fragment(),OnListItemClick {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var familyMemberList: ArrayList<familyMember> = ArrayList()
    var familyRecyclerView= FamilyMemberRecyclerView()
    lateinit var binding: FragmentAppHomeBinding
    lateinit var viewModel: AppHomeFragmentViewModel
    private lateinit var prefManager: PrefManager
    lateinit var pic: AppCompatImageView
    lateinit var post_dialog:AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(AppHomeFragmentViewModel::class.java)
        binding = FragmentAppHomeBinding.inflate(inflater,container,false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefManager=PrefManager(requireContext())
        binding.rvFamilyMembers.adapter=familyRecyclerView
//        familyMemberList.add(familyMember(0,"0","1","3"))
//        familyMemberList.add(familyMember(0,"4","5","6"))
//        familyMemberList.add(familyMember(0,"7","8","9"))
        val conManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo =conManager.activeNetworkInfo
        if((internetInfo != null) && !internetInfo.isConnected){
            Toast.makeText(context, "no internet connection", Toast.LENGTH_SHORT).show()
        }
        viewModel.getAppUserdata(prefManager.Jwt()!!)
        viewModel.appuserApiLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvUserName.text=it.first_name+" ,"+it.last_name
                binding.tvEmail.text=it.email
                Picasso.with(requireContext()).load("http://api.never-lost.tech/picture/${it.id}").into(binding.ivUserImage)

            }
        }
        viewModel.getfamily(prefManager.Jwt()!!)
        viewModel.familyLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                familyMemberList.clear()
//                familyMemberList.addAll(it)
                for(item in it){
                    familyMemberList.add(familyMember(AppCompatImageView(context!!),item.email,item.phone,item.medical_record))
                    Picasso.with(requireContext()).load("http://api.never-lost.tech/picture/${item.picture_id}").into(familyMemberList.last().photo)
                }
                familyRecyclerView.setList(familyMemberList)
            }
        }
        getFragmentManager()?.beginTransaction()?.detach(this)?.attach(this)?.commit()
        //familyRecyclerView.setList(familyMemberList)

        familyRecyclerView.onListItemClick=this
        binding.btnAddFamilyMemeber.setOnClickListener{
            if(familyRecyclerView.itemCount<3) {
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
                            familyMemberList.add(familyMember(pic,firstName,lastName,medicalcondition))
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

    override fun onItemClick(member: familyMember) {
        val Dialog=LayoutInflater.from(context).inflate(R.layout.fragment_add_family_member_dialog,null)
        val dialogBuilder= AlertDialog.Builder(context)
            .setView(Dialog)
            .setTitle("Update Family Member Data")
            .setPositiveButton("Update Member"){dialog, which ->
                Toast.makeText(context, "add", Toast.LENGTH_SHORT).show()
                val firstName=Dialog.et_first_name.text.toString()
                val lastName=Dialog.et_last_name.text.toString()
                val medicalcondition=Dialog.et_medical_condition.text.toString()

                if(firstName.isEmpty()||lastName.isEmpty()){
                    Toast.makeText(context,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                }
                else{
                    familyMemberList[familyMemberList.indexOf(member)]=(familyMember(pic,firstName,lastName,medicalcondition))
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
            alertDialog.et_first_name.setText(member.firstname)
            alertDialog.et_last_name.setText(member.lastname)
            alertDialog.et_medical_condition.setText(member.medical_history)
            alertDialog.iv_member_image.setImageDrawable(member.photo.drawable)

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

//        Toast.makeText(context,"clicked",Toast.LENGTH_LONG).show()
    }
}