package com.mohdabbas.pomodoroapp

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val focusTimeInMillis = 120000L
    private val countDownIntervalInMillis = 1000L
    private var timeLeftInMillis = 0L

    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeLeftInMillis = focusTimeInMillis

        setupOnClickListeners()
    }

    private var isTimerRunning = false
    private fun setupOnClickListeners() {
        toggleTimerButton.setOnClickListener {
            if (!isTimerRunning) {
                startTimer()
                toggleTimerButton.changeText(R.string.pause)
                restTimerButton.visibility = View.GONE
            } else {
                pauseTimer()
                toggleTimerButton.changeText(R.string.resume)
                restTimerButton.visibility = View.VISIBLE
            }
            isTimerRunning = !isTimerRunning
        }

        restTimerButton.setOnClickListener {
            restTimer()
            restTimerButton.visibility = View.GONE
            toggleTimerButton.changeText(R.string.start)
        }
    }

    private fun Button.changeText(resId: Int) {
        text = getString(resId)
    }

    private fun updateCountDownText(millisUntilFinished: Long) {
        val minutes = (millisUntilFinished / 1000) / 60
        val seconds = (millisUntilFinished / 1000) % 60

        val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)

        textView.text = timeLeftFormatted
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, countDownIntervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText(millisUntilFinished)
            }

            override fun onFinish() {
                Toast.makeText(applicationContext, "Timer is up", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
    }

    private fun restTimer() {
        countDownTimer.cancel()
        timeLeftInMillis = focusTimeInMillis
        updateCountDownText(timeLeftInMillis)
    }
}