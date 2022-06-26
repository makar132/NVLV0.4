package com.example.nvlv04.ui.fragments

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentCurrentLocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken


class currentlocationFragment : Fragment() {

  lateinit var binding: FragmentCurrentLocationBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCurrentLocationBinding.inflate(inflater,container,false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if ((ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED) or
            (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_DENIED)

        ){
            ActivityCompat.requestPermissions(
                requireContext() as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),
                123
            )
        }

        binding.btnGetlocation.setOnClickListener {
            fusedLocationClient.lastLocation
                .addOnSuccessListener{
                    Toast.makeText(context," $it", Toast.LENGTH_SHORT).show()
                }
        }


    }

}