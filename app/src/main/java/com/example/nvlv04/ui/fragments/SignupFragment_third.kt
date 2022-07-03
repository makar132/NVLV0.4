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
import androidx.navigation.fragment.findNavController
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentSignupThirdBinding
import kotlinx.android.synthetic.main.fragment_add_family_member_dialog.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

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
        binding.btnUploadPhoto.setOnClickListener {
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            GlobalScope.async {
                startActivityForResult(intent, MediaRecorder.VideoSource.CAMERA)
                onActivityResult(
                    Activity.RESULT_OK,
                    MediaRecorder.VideoSource.CAMERA,intent)
            }
        }

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupFragmentThirdViewModel::class.java)
        // TODO: Use the ViewModel
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== MediaRecorder.VideoSource.CAMERA){
            if(resultCode==Activity.RESULT_OK){
                Toast.makeText(context,"Photo Taken", Toast.LENGTH_LONG).show()
                binding.imageView.setImageBitmap((data?.extras?.get("data") as Bitmap))
            }
            else{
                Toast.makeText(context,"Photo Not Taken", Toast.LENGTH_LONG).show()
            }
        }
    }



}