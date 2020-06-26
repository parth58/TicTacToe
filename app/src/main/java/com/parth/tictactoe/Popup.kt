package com.parth.tictactoe

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewOutlineProvider
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import kotlin.math.roundToInt

class Popup : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)
        val play_Again : Button = findViewById(R.id.again)
        play_Again.setOnClickListener { finish() }



        val bundle:Bundle = intent.extras
        val player1 = bundle.get("p1")
        val player2 = bundle.get("p2")
        val score1 = bundle.get("s1")
        val score2 = bundle.get("s2")
        val win = bundle.get("win")
        val p1: TextView = findViewById(R.id.p1)
        val p2: TextView = findViewById(R.id.p2)
        val s1: TextView = findViewById(R.id.s1)
        val s2: TextView = findViewById(R.id.s2)
        val msg: TextView = findViewById(R.id.msg)

        p1.setText(player1.toString())
        p2.setText(player2.toString())
        s1.setText(score1.toString())
        s2.setText(score2.toString())
        msg.setText(win.toString())



        var dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        var width:Int = dm.widthPixels
        var height:Int = dm.heightPixels
        window.setLayout((width*0.8).roundToInt(),(height*0.5).roundToInt())
        var params:WindowManager.LayoutParams = window.attributes
        params.gravity = Gravity.CENTER
        params.x=0
        params.y=-20

        window.attributes=params



    }
}
