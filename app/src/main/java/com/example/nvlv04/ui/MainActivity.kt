package com.example.nvlv04.ui

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cuberto.liquid_swipe.LiquidPager
import com.example.nvlv04.databinding.ActivityMainBinding
import com.example.nvlv04.ui.adapter.Viewpager


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        // showing the back button in action bar
        supportActionBar?.hide()

        setContentView(binding.root)


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.accessibilityActionPageUp -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}