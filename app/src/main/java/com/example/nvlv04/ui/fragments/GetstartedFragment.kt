package com.example.nvlv04.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nvlv04.R

class GetstartedFragment : Fragment() {

    companion object {
        fun newInstance() = GetstartedFragment()
    }

    private lateinit var viewModel: GetstartedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_getstarted, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GetstartedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}