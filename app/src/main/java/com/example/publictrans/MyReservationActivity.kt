package com.example.publictrans

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MyReservationActivity : AppCompatActivity() {

    companion object{
        const val NAME="NAME"
        const val PARKINGLOT="PARKING_LOT"
        const val RESERVEDTRUE="RESERVEDTRUE"
        const val YEAR="YEAR"
        const val MONTH="MONTH"
        const val DAY="DAY"
        const val HOUR="HOUR"
        const val MINUTE="MINUTE"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_reservation)

        val name=this.intent.getStringExtra(ParkoloActivity.NAME).toString()
        val msg= "Hello $name!"
        val userName=findViewById<TextView>(R.id.UserName)
        userName.text = msg

        val resTrue=this.intent.getIntExtra(MyReservationActivity.RESERVEDTRUE, -1)
        if(resTrue==1) {

            //TODO:ez így jó?
            val date = findViewById<TextView>(R.id.Date)
            val day=this.intent.getIntExtra(ParkoloActivity.DAY,0).toString()
            val m=this.intent.getIntExtra(ParkoloActivity.MONTH,0)
            val year=this.intent.getIntExtra(ParkoloActivity.YEAR,0).toString()
            val hour=this.intent.getIntExtra(ParkoloActivity.HOUR,0).toString()
            val minute=this.intent.getIntExtra(ParkoloActivity.MINUTE,0).toString()
            val helper=m+1
            val month=helper.toString()
            date.text="$year.$month.$day $hour:$minute"
            date.setTextColor(Color.parseColor("#605F5F"))

            val parkingLot = findViewById<TextView>(R.id.parkingLot)
            parkingLot.text = this.intent.getIntExtra(MyReservationActivity.PARKINGLOT, -1).toString()
        }


        val username=this.intent.getStringExtra(MyReservationActivity.NAME).toString()
        val pLot=this.intent.getIntExtra(MyReservationActivity.PARKINGLOT, -1)


        val day2=this.intent.getIntExtra(ParkoloActivity.DAY,0)
        val m2=this.intent.getIntExtra(ParkoloActivity.MONTH,0)
        val year2=this.intent.getIntExtra(ParkoloActivity.YEAR,0)
        val hour2=this.intent.getIntExtra(ParkoloActivity.HOUR,0)
        val minute2=this.intent.getIntExtra(ParkoloActivity.MINUTE,0)

        val back=findViewById<Button>(R.id.back)
        back.setOnClickListener(){
            val i = Intent(this, ParkoloActivity::class.java)
            i.putExtra(ParkoloActivity.NAME, username)
            i.putExtra(ParkoloActivity.PARKINGLOT, pLot)
            i.putExtra(ParkoloActivity.YEAR, year2)
            i.putExtra(ParkoloActivity.MONTH, m2)
            i.putExtra(ParkoloActivity.DAY, day2)
            i.putExtra(ParkoloActivity.HOUR, hour2)
            i.putExtra(ParkoloActivity.MINUTE, minute2)
            i.putExtra(ParkoloActivity.RESERVEDTRUE, resTrue)
            i.putExtra(ParkoloActivity.BACK, 1)
            startActivity(i)
        }

    }
}