package com.example.publictrans

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import java.util.*


class SelectDateActivity : AppCompatActivity() {



    companion object{
        const val KEY_PARKING_LOT="KEY_PARKING_LOT"
        const val NAME="NAME"

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_date)

        val datePicker= findViewById<DatePicker>(R.id.datePicker)
        datePicker.minDate = System.currentTimeMillis() - 1000;
        val timePicker=findViewById<TimePicker>(R.id.timePicker)


        val parkingLot=this.intent.getIntExtra(KEY_PARKING_LOT, -1)
        val textView3= findViewById<TextView>(R.id.textView3)
        textView3.text=getWelcomeString(parkingLot)

        val username=this.intent.getStringExtra(NAME).toString()

        val reserveBtn=findViewById<Button>(R.id.reserveBtn)
        reserveBtn.setOnClickListener(){
            //TODO Reserve: Send msg to the server based on
            //TODO: Ezeket a változókat kéne a szervernek elküldeni egy stringként; datePicker.year, datePicker.month, datePicker.dayOfMonth,timePicker.minute, timePicker.hour



            val i = Intent(this, ParkoloActivity::class.java)
            i.putExtra(ParkoloActivity.LOGGED_IN, 0)
            i.putExtra(ParkoloActivity.YEAR,datePicker.year)
            i.putExtra(ParkoloActivity.MONTH,datePicker.month)
            i.putExtra(ParkoloActivity.DAY,datePicker.dayOfMonth)
            i.putExtra(ParkoloActivity.MINUTE,timePicker.minute)
            i.putExtra(ParkoloActivity.HOUR,timePicker.hour)

            i.putExtra(ParkoloActivity.PARKINGLOT,parkingLot)
            i.putExtra(ParkoloActivity.RESERVEDTRUE,1)
            i.putExtra(ParkoloActivity.NAME, username)
            startActivity(i)
        }



    }

    private fun getWelcomeString(parkingSlot: Int): String?{
        return when(parkingSlot){
            1 -> "Reserve* for 1st parking lot"
            2 -> "Reserve* for 2nd parking lot"
            3 -> "Reserve* for 3rd parking lot"
            4 -> "Reserve* for 4th parking lot"
            5 -> "Reserve* for 5th parking lot"
            6 -> "Reserve* for 6th parking lot"
            else -> "???"
        }
    }


}