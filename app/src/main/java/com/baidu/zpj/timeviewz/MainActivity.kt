package com.baidu.zpj.timeviewz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.baidu.zpj.timeviewz.R.id.time_view
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        time_view.lessTime("20180427000000",SimpleDateFormat("yyyyMMddHHmmSS"))
    }
}
