package com.example.nvlv04.ui.fragments

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nvlv04.R
import com.example.nvlv04.model.checker.checker
import com.example.nvlv04.databinding.FragmentSigninBinding
import com.example.nvlv04.model.PrefManager


class SigninFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var prefManager: PrefManager
    lateinit var binding: FragmentSigninBinding
    lateinit var checkerResult: Pair<Boolean, String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //binding = FragmentSigninBinding.inflate(inflater, container, false)
        binding= FragmentSigninBinding.inflate(inflater, container, false)
        prefManager= PrefManager(context)
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.btn_custom_back)
//        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.)))
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignIn.setOnClickListener {
            Toast.makeText(context, R.string.Todo_message, Toast.LENGTH_SHORT).show()
            Toast.makeText(context, binding.etUserName.text.toString(), Toast.LENGTH_SHORT).show()
            prefManager.setUsername(binding.etUserName.text.toString())
            findNavController().navigate(R.id.action_signinFragment_to_app_mainFragment)
        }

        binding.tvSignUp.setOnClickListener {
            Toast.makeText(context, R.string.Todo_message, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_signinFragment_to_signupFragment)
        }
        binding.tietPassword.setOnFocusChangeListener { v, hasFocus ->
            when {
                hasFocus -> {
                    checkerResult =
                        checker().checkCorrectPassword(binding.tietPassword.text.toString())
                    when {
                        !checkerResult.first -> {
                            setError(R.drawable.ic_error, checkerResult.second)
                            binding.btnSignIn.isEnabled = false
                            binding.btnSignIn.isClickable = false

                        }
                        else -> {

                            setError(R.drawable.ic_check, "")
                            binding.btnSignIn.isEnabled = true
                            binding.btnSignIn.isClickable = true

                        }
                    }
                    binding.tietPassword.addTextChangedListener {

                        checkerResult =
                            checker().checkCorrectPassword(binding.tietPassword.text.toString())
                        when {
                            !checkerResult.first -> {
                                setError(R.drawable.ic_error, checkerResult.second)
                                binding.btnSignIn.isEnabled = false
                                binding.btnSignIn.isClickable = false

                            }
                            else -> {

                                setError(R.drawable.ic_check, "")
                                binding.btnSignIn.isEnabled = true
                                binding.btnSignIn.isClickable = true

                            }
                        }
                    }
                }
                else -> {
                    setError(null, "")

                }
            }
        }

    }

    private fun setError(iconId: Int?, errorMessage: String) {
        val ic: Drawable?
        when {
            iconId != null -> {
                ic = ContextCompat.getDrawable(requireContext(), iconId)
                ic?.setBounds(0, 0, 50, 50)
            }
            else -> {
                ic = null
            }
        }
        binding.tvErrorMessage.setCompoundDrawables(ic, null, null, null)
        binding.tvErrorMessage.text = errorMessage
    }

}