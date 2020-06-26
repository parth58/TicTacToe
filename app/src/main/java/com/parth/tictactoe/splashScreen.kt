package com.parth.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import java.lang.Exception

class splashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        WindowManager.LayoutParams.FLAG_FULLSCREEN
        setContentView(R.layout.activity_splash_screen)

        val background = object :Thread(){
            override fun run() {
                try {
                    Thread.sleep(1000);
                    val intent =Intent(baseContext,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()


    }

}

