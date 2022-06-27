package com.example.nvlv04.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.nvlv04.R
import com.example.nvlv04.databinding.FragmentAppMainBinding
import kotlinx.coroutines.*

class App_mainFragment : Fragment() {
    lateinit var binding: FragmentAppMainBinding
    val homefragment = App_homeFragment()
    val reportfragment = currentlocationFragment()
    val feedfragment = App_feedFragment()
    val settingsfragment = App_settingsFragment()
    lateinit var transaction: FragmentTransaction
    var newFragment: Fragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAppMainBinding.inflate(inflater, container, false)
        when(activity?.intent?.getStringExtra("fragment"))
        {
            "report"->{
                binding.bottomBar.selectTabAt(2)
            }
        }
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val navController=binding.appMainContainer.getFragment<NavHostFragment>().navController
        //binding.bottomNavigation.setupWithNavController(navController)
        //val navController=binding.appMainContainer.getFragment<NavHostFragment>().navController
        //R.menu.tabs.
        //Toast.makeText(context, "${binding.bottomBar.selectedTab}", Toast.LENGTH_SHORT).show()

        //binding.bottomBar.selectedTab
        /*when(context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)){
            Configuration.UI_MODE_NIGHT_YES->{
                binding.bottomBar.backgroundTintList=
            }
            Configuration.UI_MODE_NIGHT_NO->{
            }

        }
        */
        newFragment = when (binding.bottomBar.selectedTab?.id) {
            R.id.app_homeFragment2 -> homefragment
            R.id.app_feedFragment2 -> feedfragment
            R.id.app_reportFragment2 -> reportfragment
            R.id.app_settingsFragment2 -> settingsfragment

            else -> null
        }
        if (newFragment != null) {
            newFragment?.onDetach()
            newFragment?.onAttach(context!!)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.app_container_second, newFragment!!)?.commit()


        }
        GlobalScope.launch(Dispatchers.IO){


            binding.bottomBar.onTabSelected = {

                newFragment = when (it.id) {
                    R.id.app_homeFragment2 -> homefragment
                    R.id.app_feedFragment2 -> feedfragment
                    R.id.app_reportFragment2 -> reportfragment
                    R.id.app_settingsFragment2 -> settingsfragment

                    else -> null
                }

                if (newFragment != null) {


                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.app_container_second, newFragment!!)?.commit()


                }
            }



        }

    }


}


