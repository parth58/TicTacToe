package com.parth.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


import android.os.Handler
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class Game_Board : AppCompatActivity() {
    lateinit var place0 :ImageView
    lateinit var place1 :ImageView
    lateinit var place2 :ImageView
    lateinit var place3 :ImageView
    lateinit var place4 :ImageView
    lateinit var place5 :ImageView
    lateinit var place6 :ImageView
    lateinit var place7 :ImageView
    lateinit var place8 :ImageView
    var gamestate = arrayOf<String>(" "," "," "," "," "," "," "," "," ")
    var move:String="o"
    lateinit var msg:TextView
    var p1:String?=null
    var p2:String?=null
    var winner:String?=null
    var s1:Int=0
    var s2:Int=0
    var single:Boolean=false
    var end:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        WindowManager.LayoutParams.FLAG_FULLSCREEN
        setContentView(R.layout.activity_game__board)


        val bundle:Bundle = intent.extras
        val ai = bundle.get("AI")

        when (ai) {
            true -> {p1="Player1"
                      p2="Computer"
                        single=true}
            false -> {p1="Player1"
                     p2="Player2"}
            else -> {
            }
        }
        place0 = findViewById(R.id.imageView1)
        place1 = findViewById(R.id.imageView2)
        place2= findViewById(R.id.imageView3)
        place3= findViewById(R.id.imageView4)
        place4= findViewById(R.id.imageView5)
        place5= findViewById(R.id.imageView6)
        place6= findViewById(R.id.imageView7)
        place7= findViewById(R.id.imageView8)
        place8= findViewById(R.id.imageView9)
        msg = findViewById(R.id.msg)

        place0.setOnClickListener { playmove(place0,0) }
        place1.setOnClickListener { playmove(place1,1) }
        place2.setOnClickListener { playmove(place2,2) }
        place3.setOnClickListener { playmove(place3,3) }
        place4.setOnClickListener { playmove(place4,4) }
        place5.setOnClickListener { playmove(place5,5) }
        place6.setOnClickListener { playmove(place6,6) }
        place7.setOnClickListener { playmove(place7,7) }
        place8.setOnClickListener { playmove(place8,8) }

        msg.setText(move.toUpperCase()+" turns")

    }

    fun playmove(img:ImageView,index:Int){
        end=false
        Log.d("listener","In Listener")
        Log.d("empty",checkEmpty(index).toString())
        when(checkEmpty(index)){
            false-> {Toast.makeText(applicationContext,"Already Occupied",Toast.LENGTH_SHORT).show()
                        return}
        }

        gamestate[index]=move

        Log.d("gamestate index",gamestate[index])
        val drawableResource = when (move) {
            "x" -> R.drawable.cross
            "o" -> R.drawable.zero
            else -> R.drawable.blank
        }
        img.setImageResource(drawableResource)
        Log.d("winner",checkWinner())
        when(checkWinner()){
            "o","x"->{

                    when(checkWinner()){
                        "o"->{winner=p1.toString()+" Wins"
                                s1=s1+1}

                        "x"->{winner=p2.toString()+" Wins"
                                s2=s2+1}
                        else->{}
                    }

                    reset()

                intent = Intent(this,Popup::class.java);

                intent.putExtra("p1", p1)
                intent.putExtra("p2", p2)
                intent.putExtra("s1", s1)
                intent.putExtra("s2", s2)
                intent.putExtra("win", winner)
                startActivity(intent)
                reset()
                end=false}
            else->{}

        }
        when(checkDraw()){
            true-> {
                msg.setText("Match Draw")

                winner="Match Draw"
                reset()
                intent = Intent(this,Popup::class.java);
                intent.putExtra("p1", p1)
                intent.putExtra("p2", p2)
                intent.putExtra("s1", s1)
                intent.putExtra("s2", s2)
                intent.putExtra("win", winner)
                startActivity(intent)
                reset()
            end=false}
            else->{}

        }

        when (move) {
            "x" -> move="o"
            "o" -> move="x"
            else -> move= ""
        }
        msg.setText(move.toUpperCase() +" turns")

        if(single==true and !end){
            aiplay()
        }
    }

    fun checkEmpty(i:Int):Boolean{

        return when (gamestate[i]) {
            "o", "x" -> false
            " " -> true
            else -> true
        }
    }

    fun checkWinner():String{
        var i:String=" "
        when(gamestate[0].equals(gamestate[1]) and gamestate[0].equals(gamestate[2])){
            true->return gamestate[0]

        }

        when(gamestate[3].equals(gamestate[4]) and gamestate[3].equals(gamestate[5])){
            true->return gamestate[3]

        }
        when(gamestate[6].equals(gamestate[7]) and gamestate[6].equals(gamestate[8])){
            true->return gamestate[6]

        }
        when(gamestate[0].equals(gamestate[6]) and gamestate[3].equals(gamestate[0])){
            true->return gamestate[0]

        }
        when(gamestate[1].equals(gamestate[4]) and gamestate[1].equals(gamestate[7])){
            true->return gamestate[1]

        }
        when(gamestate[2].equals(gamestate[5]) and gamestate[2].equals(gamestate[8])){
            true->return gamestate[5]

        }
        when(gamestate[0].equals(gamestate[4]) and gamestate[0].equals(gamestate[8])){
            true->return gamestate[4]

        }
        when(gamestate[2].equals(gamestate[4]) and gamestate[2].equals(gamestate[6])){
            true->return gamestate[4]

        }
        return i
    }

    fun reset(){
        move = "x"
        place0.setImageResource(R.drawable.blank)
        place1.setImageResource(R.drawable.blank)
        place2.setImageResource(R.drawable.blank)
        place3.setImageResource(R.drawable.blank)
        place4.setImageResource(R.drawable.blank)
        place5.setImageResource(R.drawable.blank)
        place6.setImageResource(R.drawable.blank)
        place7.setImageResource(R.drawable.blank)
        place8.setImageResource(R.drawable.blank)
        end=true


        gamestate = arrayOf<String>(" "," "," "," "," "," "," "," "," ")

    }
    fun checkDraw():Boolean{
        for(i in gamestate){
            when(i.equals(" ")){
                true->return false
            }
        }
        return true
    }

    fun aimove():Int{

        var m1:Int = winmove()
        if(m1!=-1){
            Log.d("winmove",m1.toString())
            return m1
        }
        else {
            m1 = block()
            if (m1 != -1) {
                Log.d("block move",m1.toString())
                return m1
            }
            else {

                if (gamestate[4].equals(" ")) {
                    return 4
                } else if (gamestate[0].equals(" ") or gamestate[2].equals(" ") or gamestate[6].equals(
                        " "
                    ) or gamestate[8].equals(" ")
                ) {
                    var options = intArrayOf(0, 2, 6, 8)
                    var m: Int = options.random()
                    while (!gamestate[m].equals(" ")) {
                        m = options.random()
                    }
                    return m
                } else if (gamestate[1].equals(" ") or gamestate[3].equals(" ") or gamestate[5].equals(
                        " "
                    ) or gamestate[7].equals(" ")
                ) {
                    var options = intArrayOf(1, 3, 5, 7)
                    var m: Int = options.random()
                    while (!gamestate[m].equals(" ")) {
                        m = options.random()
                    }
                    Log.d("block move",m.toString())
                    return m
                }
            }
        }
            return -1
    }
    fun block():Int{
        if(
            (gamestate[1].equals(gamestate[2]) and gamestate[1].equals("o"))
            or (gamestate[3].equals(gamestate[6])and gamestate[3].equals("o"))
            or (gamestate[4].equals(gamestate[8])and gamestate[4].equals("o"))
            and gamestate[0].equals(" ")){
            Log.d("in block move","0")
            return 0
        }
        else if(
            (gamestate[0].equals(gamestate[2])and gamestate[0].equals("o"))
            or
            (gamestate[4].equals(gamestate[7]) and gamestate[4].equals("o"))
            and gamestate[1].equals(" ")){
            Log.d("in block move","1")
            return 1
        }
        else if(
            (gamestate[1].equals(gamestate[0])and gamestate[1].equals("o"))
            or (gamestate[4].equals(gamestate[6]) and gamestate[4].equals("o"))
            or (gamestate[5].equals(gamestate[8])and gamestate[5].equals("o"))
            and gamestate[2].equals(" ")){
            Log.d("in block move","2")
            return 2
        }
        else if(
            (gamestate[0].equals(gamestate[6]) and gamestate[0].equals("o") )
            or (gamestate[4].equals(gamestate[5]) and gamestate[4].equals("o"))
            and gamestate[3].equals(" ")){
            Log.d("in block move","3")
            return 3
        }
        else if(
            (gamestate[0].equals(gamestate[8]) and gamestate[0].equals("o"))
            or (gamestate[1].equals(gamestate[7])and gamestate[1].equals("o") )
            or (gamestate[2].equals(gamestate[6])and gamestate[2].equals("o"))
            and gamestate[4].equals(" ")){
            Log.d("in block move","4")
            return 4
        }
        else if(
            (gamestate[8].equals(gamestate[2]) and gamestate[2].equals("o"))
            or (gamestate[3].equals(gamestate[4])and gamestate[3].equals("o"))
            and gamestate[5].equals(" ")){
            Log.d("in block move","5")
            return 5
        }
        else if(
            (gamestate[0].equals(gamestate[3]) and gamestate[0].equals("o"))
            or (gamestate[2].equals(gamestate[4]) and gamestate[2].equals("o"))
            or (gamestate[7].equals(gamestate[8]) and gamestate[4].equals("o"))
            and gamestate[6].equals(" ")){
            Log.d("in block move","6")

            return 6
        }
        else if(
            (gamestate[4].equals(gamestate[1]) and gamestate[4].equals("o"))
            or(gamestate[6].equals(gamestate[8]) and gamestate[6].equals("o"))
            and gamestate[7].equals(" ")){
            Log.d("in block move","7")
            return 7
        }
        else if(
            (gamestate[4].equals(gamestate[0]) and gamestate[4].equals("o"))
            or (gamestate[5].equals(gamestate[2]) and gamestate[5].equals("o"))
            or (gamestate[6].equals(gamestate[7]) and gamestate[6].equals("o"))
            and gamestate[8].equals(" ")){
            Log.d("in block move","8")
            return 8
        }
        return -1

    }






    fun winmove():Int{
        if(
            (gamestate[1].equals(gamestate[2]) and gamestate[1].equals("x"))
            or (gamestate[3].equals(gamestate[6])and gamestate[3].equals("x"))
            or (gamestate[4].equals(gamestate[8])and gamestate[4].equals("x"))
            and gamestate[0].equals(" ")){
            Log.d("in winmove","0")
            return 0
        }
        else if(
            (gamestate[0].equals(gamestate[2])and gamestate[0].equals("x"))
            or
            (gamestate[4].equals(gamestate[7]) and gamestate[4].equals("x"))
            and gamestate[1].equals(" ")){
            Log.d("in winmove","1")
            return 1
        }
        else if(
            (gamestate[1].equals(gamestate[0])and gamestate[1].equals("x"))
            or (gamestate[4].equals(gamestate[6]) and gamestate[4].equals("x"))
            or (gamestate[5].equals(gamestate[8])and gamestate[5].equals("x"))
            and gamestate[2].equals(" ")){
            Log.d("in winmove","2")
            return 2
        }
        else if(
            (gamestate[0].equals(gamestate[6]) and gamestate[0].equals("x") )
            or (gamestate[4].equals(gamestate[5]) and gamestate[4].equals("x"))
            and gamestate[3].equals(" ")){
            Log.d("in winmove","3")
            return 3
        }
        else if(
            (gamestate[0].equals(gamestate[8]) and gamestate[0].equals("x"))
            or (gamestate[1].equals(gamestate[7])and gamestate[1].equals("x") )
            or (gamestate[2].equals(gamestate[6])and gamestate[2].equals("x"))
            and gamestate[4].equals(" ")){
            Log.d("in winmove","4")

            return 4
        }
        else if(
            (gamestate[8].equals(gamestate[2]) and gamestate[2].equals("x"))
            or (gamestate[3].equals(gamestate[4])and gamestate[3].equals("x"))
            and gamestate[5].equals(" ")){
            Log.d("in winmove","5")

            return 5
        }
        else if(
            (gamestate[0].equals(gamestate[3]) and gamestate[0].equals("x"))
            or (gamestate[2].equals(gamestate[4]) and gamestate[2].equals("x"))
            or (gamestate[7].equals(gamestate[8]) and gamestate[7].equals("x"))
            and gamestate[6].equals(" ")){
            Log.d("in winmove","6")

            return 6

        }
        else if(
            (gamestate[4].equals(gamestate[1]) and gamestate[4].equals("x"))
            or(gamestate[6].equals(gamestate[8]) and gamestate[6].equals("x"))
            and gamestate[7].equals(" ")){
            Log.d("in winmove","7")
            return 7
        }
        else if(
            (gamestate[4].equals(gamestate[0]) and gamestate[4].equals("x"))
            or (gamestate[5].equals(gamestate[2]) and gamestate[5].equals("x"))
            or (gamestate[6].equals(gamestate[7]) and gamestate[6].equals("x"))
            and gamestate[8].equals(" ")){
            Log.d("in winmove","8")

            return 8
        }
        return -1

    }
    fun aiplay(){


            

            var index:Int = aimove()
            gamestate[index]=move

            Log.d(" ai gamestate index",gamestate[index])
            var img:ImageView?=null

            val drawableResource = when (move) {
                "x" -> R.drawable.cross
                "o" -> R.drawable.zero
                else -> R.drawable.blank
            }

            when(index){
                0->img=place0
                1->img=place1
                2->img=place2
                3->img=place3
                4->img=place4
                5->img=place5
                6->img=place6
                7->img=place7
                8->img=place8

            }

            if (img != null) {
                img.setImageResource(drawableResource)
            }
            when(checkWinner()){
                "o","x"->{

                    when(checkWinner()){
                        "o"->{winner=p1.toString()+" Wins"
                            s1=s1+1}

                        "x"->{winner=p2.toString()+" Wins"
                            s2=s2+1}
                        else->{}
                    }

                    reset()

                    intent = Intent(this,Popup::class.java);

                    intent.putExtra("p1", p1)
                    intent.putExtra("p2", p2)
                    intent.putExtra("s1", s1)
                    intent.putExtra("s2", s2)
                    intent.putExtra("win", winner)
                    startActivity(intent)
                    reset()
                    end=false}
                else->{}

            }
            when(checkDraw()){
                true-> {
                    msg.setText("Match Draw")

                    winner="Match Draw"
                    reset()
                    intent = Intent(this,Popup::class.java);
                    intent.putExtra("p1", p1)
                    intent.putExtra("p2", p2)
                    intent.putExtra("s1", s1)
                    intent.putExtra("s2", s2)
                    intent.putExtra("win", winner)
                    startActivity(intent)
                    reset()
                    end=false}
                else->{}

            }

            when (move) {
                "x" -> move="o"
                "o" -> move="x"
                else -> move= ""
            }



            msg.setText(move.toUpperCase() +" turns")



    }


}
