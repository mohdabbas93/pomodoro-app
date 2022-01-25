package com.mohdabbas.pomodoroapp

import android.os.Bundle
import android.os.CountDownTimer
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
        toggoleTimerButton.setOnClickListener {
            if (!isTimerRunning) {
                startTimer()
                toggoleTimerButton.changeText(R.string.pause)
            } else {
                pauseTimer()
                toggoleTimerButton.changeText(R.string.start)
            }
            isTimerRunning = !isTimerRunning
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
}