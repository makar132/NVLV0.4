package com.example.nvlv04.ui.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentAppOnboardingBinding
import com.example.nvlv04.model.PrefManager
import com.example.nvlv04.ui.adapter.OnboardingViewPagerAdapter
import com.example.nvlv04.ui.adapter.Viewpager
import com.example.nvlv04.utils.Animatoo
import com.google.android.material.tabs.TabLayoutMediator


private lateinit var mViewPager: ViewPager2
private lateinit var textSkip: TextView

class App_onboardingFragment : Fragment() {

    companion object {
        fun newInstance() = App_onboardingFragment()
    }

    private lateinit var viewModel: AppOnboardingViewModel
    lateinit var binding: FragmentAppOnboardingBinding
    lateinit var prefManager: PrefManager
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAppOnboardingBinding.inflate(inflater,container,false)
       mViewPager = binding.viewPager
       mViewPager.adapter = OnboardingViewPagerAdapter(activity!!, context!!)
       TabLayoutMediator(binding.pageIndicator, mViewPager) { _, _ -> }.attach()
       textSkip = binding.textSkip
       textSkip.setOnClickListener {
           findNavController().navigate(R.id.action_app_onboardingFragment_to_onboardingFinishFragment)
       }

       val btnNextStep: Button = binding.btnNextStep

       btnNextStep.setOnClickListener {
           Toast.makeText(context, "${mViewPager.childCount}, ${getItem()}", Toast.LENGTH_SHORT).show()
           if (getItem() > 0) {
               findNavController().navigate(R.id.action_app_onboardingFragment_to_onboardingFinishFragment)
               //Animatoo.animateSlideRight(context!!)
           } else {
               mViewPager.setCurrentItem(getItem() + 1, true)
           }
       }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            if(getItem()==0){
                activity?.finish()}
            else{
                mViewPager.setCurrentItem(getItem() - 1, true)

            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[AppOnboardingViewModel::class.java]
        // TODO: Use the ViewModel

    }



    private fun getItem(): Int {
        return mViewPager.currentItem
    }
}