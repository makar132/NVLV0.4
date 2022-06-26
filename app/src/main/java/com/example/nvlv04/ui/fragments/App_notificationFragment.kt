package com.example.nvlv04.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.nvlv04.databinding.FragmentAppNotificationBinding
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.nvlv04.ui.MainActivity
import com.example.nvlv04.R


class App_notificationFragment : Fragment() {
    lateinit var binding: FragmentAppNotificationBinding
    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIF_ID = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAppNotificationBinding.inflate(inflater,container,false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNotifChannel()

        val intent=Intent(requireContext(), MainActivity::class.java)
        intent.putExtra("fragment","report")
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notif = NotificationCompat.Builder(requireContext(),CHANNEL_ID)
            .setContentTitle("Sample Title")
            .setContentText("This is sample body notif")
            .setSmallIcon(R.drawable.custom_user_icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()


        val notifManger = NotificationManagerCompat.from(requireContext())


        binding.btnShowNotif.setOnClickListener {
            notifManger.notify(NOTIF_ID,notif)
        }
    }

    private fun createNotifChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }


}