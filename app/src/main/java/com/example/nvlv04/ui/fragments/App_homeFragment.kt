package com.example.nvlv04.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentAppHomeBinding
import com.example.nvlv04.model.entity.familyMember
import com.example.nvlv04.model.remote.remoteRepoImp
import com.example.nvlv04.model.remote.retroBuilder
import com.example.nvlv04.model.remote.serviceApi
import com.example.nvlv04.ui.adapter.FamilyMemberRecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [App_homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class App_homeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var familyMemberList: ArrayList<familyMember> = ArrayList()
    var familyRecyclerView= FamilyMemberRecyclerView()
    lateinit var binding: FragmentAppHomeBinding
    lateinit var viewModel: AppHomeFragmentViewModel
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


        binding.rvFamilyMembers.adapter=familyRecyclerView
//        familyMemberList.add(familyMember(0,"0","1","3"))
//        familyMemberList.add(familyMember(0,"4","5","6"))
//        familyMemberList.add(familyMember(0,"7","8","9"))
        val conManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo =conManager.activeNetworkInfo
        if((internetInfo != null) && internetInfo.isConnected!=true){
            Toast.makeText(context, "no internet connection", Toast.LENGTH_SHORT).show()
        }
        viewModel.getAppUserApi("1")
        viewModel.appuserApiLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvUserName.text=it.first_name+" ,"+it.last_name
                binding.tvEmail.text=it.email


            }
        }
        familyRecyclerView.setList(familyMemberList)

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment App_homeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            App_homeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}