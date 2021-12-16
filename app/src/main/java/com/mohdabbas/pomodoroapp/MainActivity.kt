package com.mohdabbas.pomodoroapp

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        startButton.setOnClickListener {
            setupCountDownTimer(120000) // Count down timer is set for 2 minutes just as a starting point
        }
    }

    private fun setupCountDownTimer(durationInMillis: Long) {
        object : CountDownTimer(durationInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountDownText(millisUntilFinished)
            }

            override fun onFinish() {
                Toast.makeText(applicationContext, "Timer is up", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun updateCountDownText(millisUntilFinished: Long) {
        val minutes = (millisUntilFinished / 1000) / 60
        val seconds = (millisUntilFinished / 1000) % 60

        val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)

        textView.text = timeLeftFormatted
    }
}