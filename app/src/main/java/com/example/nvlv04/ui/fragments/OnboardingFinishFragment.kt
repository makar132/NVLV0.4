package com.example.nvlv04.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentOnboardingFinishBinding
import com.example.nvlv04.model.PrefManager
import com.example.nvlv04.utils.Animatoo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OnboardingFinishFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnboardingFinishFragment : Fragment() {
    lateinit var prefManager: PrefManager
    lateinit var binding: FragmentOnboardingFinishBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingFinishBinding.inflate(inflater, container, false)
        prefManager = PrefManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutStart.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFinishFragment_to_app_mainFragment)
            prefManager.setOnboarding()
//            Animatoo.animateSlideLeft(context!!)
            Animatoo.animateSpin(context!!)
        }
    }


}