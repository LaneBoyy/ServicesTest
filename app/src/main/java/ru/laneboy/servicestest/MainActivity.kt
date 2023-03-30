package ru.laneboy.servicestest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.laneboy.servicestest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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
            startService(MyService.newIntent(this))
        }
    }
}