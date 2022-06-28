package com.example.nvlv04.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentAppFeedBinding
import com.example.nvlv04.model.entity.feedPost
import com.example.nvlv04.ui.adapter.feedPostRecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [App_feedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class App_feedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var feed: ArrayList<feedPost> = ArrayList()
    var feedRecyclerView= feedPostRecyclerView()
    lateinit var binding: FragmentAppFeedBinding
    lateinit var viewModel: AppFeedFragmentViewModel
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
        binding = FragmentAppFeedBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(AppFeedFragmentViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFamilyMembers.adapter=feedRecyclerView
        feed.add(feedPost(resources.getDrawable(R.drawable.profile_avatar),"Nvl"))
        feed.add(feedPost(resources.getDrawable(R.drawable.profile_avatar),"first post"))
        feed.add(feedPost(resources.getDrawable(R.drawable.profile_avatar),"third post"))
        feed.add(feedPost(resources.getDrawable(R.drawable.profile_avatar),"second post"))
        feed.add(feedPost(resources.getDrawable(R.drawable.profile_avatar),"fourth post"))
        feedRecyclerView.setList(feed)

        val conManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo =conManager.activeNetworkInfo
        if((internetInfo != null) && !internetInfo.isConnected){
            Toast.makeText(context, "no internet connection", Toast.LENGTH_SHORT).show()
        }
        viewModel.getuser("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwibmF0aW9uYWxfaWQiOiIxMjM0NTY3ODkxMDIzNCIsInVzZXJfdHlwZSI6ImZhbWlseV9hZG1pbiIsImlhdCI6MTY1NjI4NDc3NH0.Lzx6JGthMFUZs_y_JtuovZQxdTpaAy6oCpf8A664HG0")
        viewModel.appuserApiLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                //feedRecyclerView.setList(it)
                Toast.makeText(context, "${it.id}", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, it.username, Toast.LENGTH_SHORT).show()
                Toast.makeText(context, it.first_name, Toast.LENGTH_SHORT).show()
                Toast.makeText(context, it.last_name, Toast.LENGTH_SHORT).show()

            }

        }
//        feedRecyclerView.setList(feed)

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment App_feedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            App_feedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}