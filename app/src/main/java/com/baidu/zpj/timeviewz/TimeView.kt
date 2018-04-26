package com.baidu.zpj.timeviewz

import android.content.Context
import android.os.Handler
import android.text.format.Time
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Created by 赵鹏军
 *  on 2018/4/26 0026.
 */
class TimeView : TextView {

    private val timeHandler = Handler()


    private var timeAddRunnable: TimeAddRunnable? = null
    private var timeLessRunnable: TimeLessRunnable? = null
    private lateinit var format: SimpleDateFormat

    constructor (context: Context) : super(context)
    constructor (context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor (context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var isLoading = false
    private var showTimeLong = 0L
    /**
     * 时间前进
     * 当前时间减去开始时间，
     */
    fun addTime(startTime: Long) {
        if (timeAddRunnable == null) {
            timeAddRunnable = TimeAddRunnable()
        }
        showTimeLong = System.currentTimeMillis() - startTime
        if (!isLoading) {
            timeHandler.postDelayed(timeAddRunnable, 1000)
        }
    }

    /**
     * 时间前进
     * 当前时间减去开始时间，
     */
    fun addTime(startTime: String, format: DateFormat) {
        if (timeAddRunnable == null) {
            timeAddRunnable = TimeAddRunnable()
        }
        val startTimeLong = format.parse(startTime).time
        showTimeLong = System.currentTimeMillis() - startTimeLong
        Log.d("时间差距","$showTimeLong")
        if (!isLoading) {
            timeHandler.postDelayed(timeAddRunnable, 1000)
        }
    }

    /**
     * 倒计时
     * 结束时间减去当前时间
     */
    fun lessTime(endTime: Long) {
        if (timeLessRunnable == null) {
            timeLessRunnable = TimeLessRunnable()
        }

        showTimeLong = endTime - System.currentTimeMillis()
        if (!isLoading) {
            timeHandler.postDelayed(timeLessRunnable, 1000)
        }
    }

    /**
     * 倒计时
     * 结束时间减去当前时间
     */
    fun lessTime(endTime: String, format: SimpleDateFormat) {
        if (timeLessRunnable == null) {
            timeLessRunnable = TimeLessRunnable()
        }
        val endTimeLong = format.parse(endTime).time
        showTimeLong = endTimeLong - System.currentTimeMillis()
        if (!isLoading) {
            timeHandler.postDelayed(timeLessRunnable, 1000)
        }
    }

    fun cancleTime() {
        if (timeAddRunnable != null) {
            timeHandler.removeCallbacks(timeAddRunnable)
        }
        if (timeLessRunnable != null) {
            timeHandler.removeCallbacks(timeLessRunnable)
        }
    }

    fun Long.timeLong2String(): String {
        val hour = this / 1000 / 60 / 60
        val min = this / 1000 /60 %60
        val seconds = this / 1000 % 60%60
        val showHour: String
        val showMIn: String
        val showSeconds: String

        showHour = if (hour < 10) {
            "0$hour"
        } else {
            "$hour"
        }
        showMIn = if (min < 10) {
            "0$min"
        } else {
            "$min"
        }

        showSeconds = if (seconds < 10) {
            "0$seconds"
        } else {
            "$seconds"
        }
        return "$showHour : $showMIn : $showSeconds"
    }

    private inner class TimeLessRunnable : Runnable {
        override fun run() {
            isLoading = true
            showTimeLong -= 1000
            text = showTimeLong.timeLong2String()
            timeHandler.postDelayed(timeLessRunnable, 1000)
        }
    }

    private inner class TimeAddRunnable : Runnable {
        override fun run() {
            isLoading = true
            showTimeLong += 1000
            text = showTimeLong.timeLong2String()
            timeHandler.postDelayed(timeAddRunnable, 1000)
        }
    }
}