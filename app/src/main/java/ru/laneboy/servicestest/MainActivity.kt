package ru.laneboy.servicestest

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.laneboy.servicestest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        requestNotificationPermission()
        clickOnBtnService()
    }

    private fun clickOnBtnService() {

        binding.btnSimpleService.setOnClickListener {
            startService(MyService.newIntent(this, 25))
        }
        binding.btnForegroundService.setOnClickListener {
            ContextCompat.startForegroundService(
                this,
                MyForegroundService.newIntent(this)
            )
        }
    }

    private fun requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                "android.permission.POST_NOTIFICATIONS"
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf("android.permission.POST_NOTIFICATIONS"),
                PERMISSIONS_REQUEST_POST_NOTIFICATIONS
            )
        }
    }

    companion object {

        const val PERMISSIONS_REQUEST_POST_NOTIFICATIONS = 1
    }
}