package ru.laneboy.servicestest

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.laneboy.servicestest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var id = 0

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clickOnBtnService()
    }

    private fun clickOnBtnService() {

        binding.btnSimpleService.setOnClickListener {
            startService(MyService.newIntent(this, 25))
        }
        binding.btnForegroundService.setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {
        val notificationChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val notification = Notification.Builder(this)
            .setContentTitle("Title")
            .setContentText("Content text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(id++, notification)
    }

    companion object {

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
    }
}