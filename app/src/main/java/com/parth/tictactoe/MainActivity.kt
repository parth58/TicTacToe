package com.parth.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var AI = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        WindowManager.LayoutParams.FLAG_FULLSCREEN
        setContentView(R.layout.activity_main)
        val one_player : Button = findViewById(R.id.singlePlayer)
        one_player.setOnClickListener { setAI() }
        val two_player : Button = findViewById(R.id.twoPlayer)
        two_player.setOnClickListener { resetAI() }

    }
    private fun setAI() {
        AI=true
        intent = Intent(this,Game_Board::class.java)
        intent.putExtra("AI", AI)
        startActivity(intent)
    }
    private fun resetAI() {
        AI=false
        intent = Intent(this,Game_Board::class.java)
        intent.putExtra("AI", AI)
        startActivity(intent)
    }
}
