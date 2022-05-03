package com.example.publictrans

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ReserveActivity : AppCompatActivity() {

    companion object {
        const val NAME="NAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)

        val lot1=findViewById<Button>(R.id.radioButton1)
        val lot2=findViewById<Button>(R.id.radioButton2)
        val lot3=findViewById<Button>(R.id.radioButton3)
        val lot4=findViewById<Button>(R.id.radioButton4)
        val lot5=findViewById<Button>(R.id.radioButton5)
        val lot6=findViewById<Button>(R.id.radioButton6)
        var selected=0

        lot1.setOnClickListener(){
            lot1.setBackgroundColor(Color.parseColor("#FF53B80B"))
            lot2.setBackgroundColor(Color.parseColor("#F14813"))
            lot3.setBackgroundColor(Color.parseColor("#F14813"))
            lot4.setBackgroundColor(Color.parseColor("#F14813"))
            lot5.setBackgroundColor(Color.parseColor("#F14813"))
            lot6.setBackgroundColor(Color.parseColor("#F14813"))
            selected=1
        }

        lot2.setOnClickListener(){
            lot2.setBackgroundColor(Color.parseColor("#FF53B80B"))
            lot1.setBackgroundColor(Color.parseColor("#F14813"))
            lot3.setBackgroundColor(Color.parseColor("#F14813"))
            lot4.setBackgroundColor(Color.parseColor("#F14813"))
            lot5.setBackgroundColor(Color.parseColor("#F14813"))
            lot6.setBackgroundColor(Color.parseColor("#F14813"))
            selected=2
        }

        lot3.setOnClickListener(){
            lot3.setBackgroundColor(Color.parseColor("#FF53B80B"))
            lot2.setBackgroundColor(Color.parseColor("#F14813"))
            lot1.setBackgroundColor(Color.parseColor("#F14813"))
            lot4.setBackgroundColor(Color.parseColor("#F14813"))
            lot5.setBackgroundColor(Color.parseColor("#F14813"))
            lot6.setBackgroundColor(Color.parseColor("#F14813"))
            selected=3
        }

        lot4.setOnClickListener(){
            lot4.setBackgroundColor(Color.parseColor("#FF53B80B"))
            lot2.setBackgroundColor(Color.parseColor("#F14813"))
            lot3.setBackgroundColor(Color.parseColor("#F14813"))
            lot1.setBackgroundColor(Color.parseColor("#F14813"))
            lot5.setBackgroundColor(Color.parseColor("#F14813"))
            lot6.setBackgroundColor(Color.parseColor("#F14813"))
            selected=4
        }

        lot5.setOnClickListener(){
            lot5.setBackgroundColor(Color.parseColor("#FF53B80B"))
            lot2.setBackgroundColor(Color.parseColor("#F14813"))
            lot3.setBackgroundColor(Color.parseColor("#F14813"))
            lot4.setBackgroundColor(Color.parseColor("#F14813"))
            lot1.setBackgroundColor(Color.parseColor("#F14813"))
            lot6.setBackgroundColor(Color.parseColor("#F14813"))
            selected=5
        }

        lot6.setOnClickListener(){
            lot6.setBackgroundColor(Color.parseColor("#FF53B80B"))
            lot2.setBackgroundColor(Color.parseColor("#F14813"))
            lot3.setBackgroundColor(Color.parseColor("#F14813"))
            lot4.setBackgroundColor(Color.parseColor("#F14813"))
            lot5.setBackgroundColor(Color.parseColor("#F14813"))
            lot1.setBackgroundColor(Color.parseColor("#F14813"))
            selected=6
        }


        val username=this.intent.getStringExtra(ReserveActivity.NAME).toString()

        val nextBtn=findViewById<Button>(R.id.nextBtn)
        nextBtn.setOnClickListener(){
            if(selected==0) {
                val msg = "Please select a parking lot!!"
                val toast = Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                val i = Intent(this, SelectDateActivity::class.java)
                i.putExtra(SelectDateActivity.KEY_PARKING_LOT, selected)
                i.putExtra(SelectDateActivity.NAME, username)
                startActivity(i)
            }

        }
    }
}