package com.example.nvlv04.ui.fragments

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.budiyev.android.codescanner.*
import com.example.nvlv04.databinding.FragmentServiceQrCodeBinding
import kotlinx.coroutines.*

class service_QrCodeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentServiceQrCodeBinding
    lateinit var codeScanner: CodeScanner
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            // Do something if permission granted
            if (isGranted) {
                Log.i("DEBUG", "permission granted")
            } else {
                Log.i("DEBUG", "permission denied")
            }
        }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
                binding=FragmentServiceQrCodeBinding.inflate(inflater, container, false)
       /* GlobalScope.launch(Dispatchers.IO) {

                codeScanner = context?.let { CodeScanner(it, binding.scannerview) }!!

        }*/


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var handler: Handler
        GlobalScope.launch(Dispatchers.Main) {
            binding.progressBar.visibility = View.VISIBLE
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

                        codeScanner= CodeScanner(requireContext(),binding.scannerview)
                    withContext(Dispatchers.IO) {


                        codeScanner.camera = CodeScanner.CAMERA_BACK
                        codeScanner.formats = CodeScanner.ALL_FORMATS
                        codeScanner.autoFocusMode = AutoFocusMode.SAFE
                        codeScanner.scanMode = ScanMode.CONTINUOUS
                        codeScanner.isAutoFocusEnabled = true
                        codeScanner.isFlashEnabled = false
                        withContext(Dispatchers.Main){
                            codeScanner.decodeCallback = DecodeCallback {
                                qrCallback(it.text)

                            }
                            codeScanner.errorCallback = ErrorCallback {
                                qrErrorCallback(it.message)
                            }
                        }

                        codeScanner.startPreview()
                    }
                }.await()
            }
            binding.progressBar.visibility = View.GONE


        }


    }

    fun qrCallback(text: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun qrErrorCallback(text: String?) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

/*
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Toast.makeText(context, permissions[0], Toast.LENGTH_SHORT).show()
        Toast.makeText(context, permissions[1], Toast.LENGTH_SHORT).show()

        if(requestCode==123){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                codeScanner= CodeScanner(requireContext(),binding.scannerview)
                codeScanner.camera=CodeScanner.CAMERA_BACK
                codeScanner.formats=CodeScanner.ALL_FORMATS
                codeScanner.autoFocusMode=AutoFocusMode.SAFE
                codeScanner.scanMode=ScanMode.CONTINUOUS
                codeScanner.isAutoFocusEnabled=true
                codeScanner.isFlashEnabled=false
                codeScanner.decodeCallback= DecodeCallback {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, it.text, Toast.LENGTH_SHORT).show()
                    }

                }
                codeScanner.errorCallback= ErrorCallback {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
*/

    override fun onResume() {
        super.onResume()
        binding.progressBar.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            if (::codeScanner.isInitialized) {
                async {

                    withContext(Dispatchers.IO) {

                        codeScanner.startPreview()
                    }
                }.await()
            }

        }
        binding.progressBar.visibility = View.GONE


    }

    override fun onPause() {
        GlobalScope.launch(Dispatchers.Main) {
            if (::codeScanner.isInitialized) {
                binding.progressBar.visibility = View.VISIBLE
                async {

                    withContext(Dispatchers.IO) {

                        codeScanner.releaseResources()
                    }
                }.await()
            }
            binding.progressBar.visibility = View.GONE

        }

        super.onPause()


    }


}