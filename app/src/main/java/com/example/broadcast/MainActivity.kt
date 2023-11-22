package com.example.broadcast

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.broadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelId = "TEST_NOTIF"
    private val noifId = 90

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        binding.btnNotification.setOnClickListener{
            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                PendingIntent.FLAG_IMMUTABLE
            }
            else{
                0
            }

//            val intent = Intent(this,
//                NotifReceiver::class.java)
//                .putExtra("MESSAGE", "Baca selengkapnya ...")
            val intent = Intent(this, MainActivity2::class.java)

            val pendingIntent = PendingIntent.getActivity(this,
                0, intent, flag)

            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Notification Test")
//                .setContentText("Hello World!")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(0,"Baca Notif", pendingIntent)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText("Hello Wroldwkmdxknwccw c ndjnd"))

            val notifManager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val notifChannel = NotificationChannel(
                    channelId,
                    "Notification Test",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                with(notifManager){
                    createNotificationChannel(notifChannel)
                    notify(0, builder.build())
                }
            }
            else{
                notifManager.notify(0, builder.build())
            }
        }

        binding.btnUpdate.setOnClickListener{
            val notifImage = BitmapFactory.decodeResource(resources,R.drawable.img2)

            val builder = NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Notifku")
                .setContentText("Ini Update Notifikasi")
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(notifImage)
                )
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            notifManager.notify(noifId, builder.build())
        }
    }
}