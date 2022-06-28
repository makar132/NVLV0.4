package com.example.nvlv04.ui.fragments

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaRecorder.VideoSource.CAMERA
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.ScanMode
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentAppReportBinding
import com.example.nvlv04.model.entity.familyMember
import kotlinx.android.synthetic.main.app_report_reportfound.view.*
import kotlinx.android.synthetic.main.app_report_reportfound_post.*
import kotlinx.android.synthetic.main.app_report_reportmissing.*
import kotlinx.android.synthetic.main.fragment_service__qr_code.*
import kotlinx.android.synthetic.main.fragment_service__qr_code.view.*
import kotlinx.coroutines.*

class App_reportFragment : Fragment() {
    // TODO: Rename and change types of parameters
  lateinit var binding:FragmentAppReportBinding
  lateinit var codeScanner: CodeScanner
  lateinit var pic:AppCompatImageView
  lateinit var post_dialog:AlertDialog
  lateinit var familymembers:ArrayList<familyMember>
    lateinit var familymembersnames:ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        familymembers=ArrayList<familyMember>()
        familymembersnames=ArrayList<String>()
        familymembersnames.add("a" + " ," + "a")
        familymembersnames.add("b" + " ," + "b")
        familymembersnames.add("c" + " ," + "c")
        binding=FragmentAppReportBinding.inflate(inflater,container,false)
        binding.btnReportFound.setOnClickListener {

            val Dialog=LayoutInflater.from(context).inflate(R.layout.app_report_reportfound,null)
            //val Dialog=service_QrCodeFragment()
            val dialogBuilder= AlertDialog.Builder(context)
                .setView(Dialog)
                .setTitle("Report Found Methods")
            val alertDialog=dialogBuilder.create()
            alertDialog.setOnShowListener {

                Dialog.btn_report_found_scan_qr.setOnClickListener {
                    val Dialog_qr=LayoutInflater.from(context).inflate(R.layout.fragment_service__qr_code,null)
                    val dialogBuilder_qr= AlertDialog.Builder(context)
                        .setView(Dialog_qr)
                    val alertDialog_qr=dialogBuilder_qr.create()
                    alertDialog_qr.setOnShowListener {

                        /*codeScanner=CodeScanner(requireContext(),Dialog.scannerview)
                        codeScanner.setDecodeCallback {
                            alertDialog.dismiss()
                            Toast.makeText(context,"QR Code: ${it.text}",Toast.LENGTH_LONG).show()
                        }
                        codeScanner.startPreview()*/
                        /*Dialog.codeScanner.setDecodeCallback {
                            alertDialog.dismiss()
                            Toast.makeText(context,"QR Code: ${it.text}",Toast.LENGTH_LONG).show()
                        }*/

                        GlobalScope.launch(Dispatchers.Main) {
                            if (ContextCompat.checkSelfPermission(
                                    requireContext(),
                                    Manifest.permission.CAMERA
                                ) == PackageManager.PERMISSION_DENIED
                            ) {
                                ActivityCompat.requestPermissions(
                                    requireContext() as Activity,
                                    arrayOf(Manifest.permission.CAMERA),
                                    123
                                )
                                //        requestPermission.launch(Manifest.permission.CAMERA)
                            } else {

                                async {

                                    codeScanner= CodeScanner(requireContext(),Dialog_qr.scannerview)
                                    withContext(Dispatchers.IO) {


                                        codeScanner.camera = CodeScanner.CAMERA_BACK
                                        codeScanner.formats = CodeScanner.ALL_FORMATS
                                        codeScanner.autoFocusMode = AutoFocusMode.SAFE
                                        codeScanner.scanMode = ScanMode.CONTINUOUS
                                        codeScanner.isAutoFocusEnabled = true
                                        codeScanner.isFlashEnabled = false
                                        withContext(Dispatchers.Main){
                                            codeScanner.setDecodeCallback {
                                                alertDialog.dismiss()
                                                Toast.makeText(context,"QR Code: ${it.text}",Toast.LENGTH_LONG).show()
                                            }
                                            codeScanner.setErrorCallback {
                                                Toast.makeText(context,"Error: ${it.message}",Toast.LENGTH_LONG).show()
                                            }
                                        }

                                        codeScanner.startPreview()
                                    }
                                }.await()
                            }


                        }

                    }


                    alertDialog_qr.show()

                }
                Dialog.btn_report_found_post_to_feed.setOnClickListener {
                    val Dialog_post=LayoutInflater.from(context).inflate(R.layout.app_report_reportfound_post,null)

                    val dialogBuilder_post= AlertDialog.Builder(context)
                        .setView(Dialog_post)
                        .setTitle("Post to Feed")
                        .setPositiveButton("Post"){dialog, which ->
                            Toast.makeText(context,"Post",Toast.LENGTH_LONG).show()
                        }
                        .setNegativeButton("Cancel"){dialog, which ->
                            dialog.dismiss()
                        }
                    val alertDialog_post=dialogBuilder_post.create()
                    alertDialog_post.setOnShowListener {

                        alertDialog_post.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=false
                        alertDialog_post.getButton(AlertDialog.BUTTON_POSITIVE).isClickable=false
                        pic=alertDialog_post.iv_post_image
                        post_dialog=alertDialog_post
                        alertDialog_post.et_description.addTextChangedListener {
                            if(it.toString().isNotEmpty()&&it.toString().isNotBlank() && pic.drawable!=null ){
                                Toast.makeText(context, "${pic.drawable}", Toast.LENGTH_SHORT).show()
                                alertDialog_post.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=true
                                alertDialog_post.getButton(AlertDialog.BUTTON_POSITIVE).isClickable=true
                            }
                            else{
                                alertDialog_post.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=false
                                alertDialog_post.getButton(AlertDialog.BUTTON_POSITIVE).isClickable=false
                            }
                        }

                        alertDialog_post.btn_upload_photo.setOnClickListener {
                            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            GlobalScope.async {
                                startActivityForResult(intent,CAMERA)
                                onActivityResult(Activity.RESULT_OK,CAMERA,intent)
                            }

//                            Toast.makeText(context, "after image", Toast.LENGTH_SHORT).show()

//                            startActivityForResult(intent,CAMERA)
                            //onActivityResult(CAMERA,CAMERA,intent)
                        }
                    }

                    alertDialog_post.show()

                }


            }
            alertDialog.show()

        }
        binding.btnReportMissing.setOnClickListener {

            val Dialog=LayoutInflater.from(context).inflate(R.layout.app_report_reportmissing,null)
            //val Dialog=service_QrCodeFragment()
            val dialogBuilder= AlertDialog.Builder(context)
                .setView(Dialog)
                .setTitle("Report Missing")
            val alertDialog=dialogBuilder.create()
            alertDialog.setOnShowListener {
                alertDialog.dropdown_editable.setAdapter(
                    ArrayAdapter<String>(
                        requireContext(),
                    R.layout.app_report_reportmissing_dropdowm_item,
                    familymembersnames
                    )
                )
                alertDialog.btn_report_missing.setOnClickListener {
                    Toast.makeText(context, alertDialog.dropdown_editable.text.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            alertDialog.show()

        }



        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==CAMERA){
            if(resultCode==Activity.RESULT_OK){
                Toast.makeText(context,"Photo Taken",Toast.LENGTH_LONG).show()
                pic.setImageBitmap((data?.extras?.get("data") as Bitmap))
                if(pic.drawable!=null && post_dialog.et_description.text.toString().isNotEmpty()&&post_dialog.et_description.text.toString().isNotBlank()){
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