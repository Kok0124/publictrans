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

            val date = findViewById<TextView>(R.id.Date)
            val endDate = findViewById<TextView>(R.id.endDate)


            var day=this.intent.getIntExtra(ParkoloActivity.DAY,0)
            var month=this.intent.getIntExtra(ParkoloActivity.MONTH,0)
            month+=1
            val year=this.intent.getIntExtra(ParkoloActivity.YEAR,0)
            var hour=this.intent.getIntExtra(ParkoloActivity.HOUR,0)
            var minute=this.intent.getIntExtra(ParkoloActivity.MINUTE,0)

            val yearSt=year.toString()

            var monthSt=month.toString()
            if (month<10) {
                monthSt="0$monthSt"
            }

            var daySt=day.toString()
            if (day<10) {
                daySt="0$daySt"
            }

            var hourSt=hour.toString()
            if (hour<10) {
                hourSt="0$hourSt"
            }

            var minuteSt=minute.toString()
            if (minute<10) {
                minuteSt="0$minuteSt"
            }




            date.text="$yearSt.$monthSt.$daySt $hourSt:$minuteSt"
            date.setTextColor(Color.parseColor("#605F5F"))
            endDate.setTextColor(Color.parseColor("#605F5F"))

            if(minute>=30){
                hour+=1
                minute+=30
                minute %= 60
                minuteSt=minute.toString()
                hourSt=hour.toString()
            }
            else{
                minute+=30
                minuteSt=minute.toString()
            }
            endDate.text="$yearSt.$monthSt.$daySt $hourSt:$minuteSt"

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
            finish()
        }

    }
}