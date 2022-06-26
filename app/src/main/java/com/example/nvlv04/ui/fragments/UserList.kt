package com.example.nvlv04.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentUserListBinding
import com.example.nvlv04.model.PrefManager
import com.example.nvlv04.model.entity.User
import com.example.nvlv04.ui.adapter.UserRecyclerView

class UserList : Fragment() {
lateinit var binding: FragmentUserListBinding
lateinit var prefManager: PrefManager
var userRecyclerView=UserRecyclerView()
    companion object {
        fun newInstance() = UserList()
    }

    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentUserListBinding.inflate(inflater,container,false)
        prefManager= PrefManager(context)
        prefManager.setUsername("Ahmed Mohamed")
        prefManager.setPassword("123abc")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        binding.rvMessageList.adapter=userRecyclerView

            val conManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val internetInfo =conManager.activeNetworkInfo
            if((internetInfo != null) && internetInfo.isConnected){
                Toast.makeText(context, "internet", Toast.LENGTH_SHORT).show()
            }

        viewModel.addUserApi(User(100,
            prefManager.getUsername()!!, prefManager.getPassword()!!
        ))
        //Toast.makeText(context, "${prefManager.getUsername()!!} ::  ${prefManager.getPassword()!!}", Toast.LENGTH_SHORT).show()
        userRecyclerView.setList(listOf(User(0,"None","None")))
        //Toast.makeText(context, "a${viewModel.u}", Toast.LENGTH_SHORT).show()
        viewModel.adduserApiLiveData.observe(viewLifecycleOwner, Observer {
            //Toast.makeText(context, "ss${viewModel.u}", Toast.LENGTH_SHORT).show()
            //Toast.makeText(context, "${it}", Toast.LENGTH_SHORT).show()
            if(it!=null){
              //  Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
            }
            else Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
            userRecyclerView.setList(listOf(it))
        })
        //Toast.makeText(context, "d${viewModel.u}", Toast.LENGTH_SHORT).show()
        //Toast.makeText(context, "end", Toast.LENGTH_SHORT).show()

    }

}