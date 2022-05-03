package com.example.publictrans

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class ParkoloActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")

    companion object{
        const val LOGGED_IN="LOGGED_IN"
        const val NAME="NAME"
        const val YEAR="YEAR"
        const val MONTH="MONTH"
        const val DAY="DAY"
        const val HOUR="HOUR"
        const val MINUTE="MINUTE"
        const val PARKINGLOT="PARKING_LOT"
        const val RESERVEDTRUE="RESERVEDTRUE"
        const val BACK="BACK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parkolo)

        val loggedIn=this.intent.getIntExtra(LOGGED_IN, -1)
        val back=this.intent.getIntExtra(BACK, -1)
        val username=this.intent.getStringExtra(NAME) //.toString()
        val pLot=this.intent.getIntExtra(PARKINGLOT, -1)
        val resTrue=this.intent.getIntExtra(RESERVEDTRUE, -1)

        val day=this.intent.getIntExtra(DAY,0).toString()
        val m=this.intent.getIntExtra(MONTH,0)
        val year=this.intent.getIntExtra(YEAR,0).toString()
        val hour=this.intent.getIntExtra(HOUR,0).toString()
        val minute=this.intent.getIntExtra(MINUTE,0).toString()

        val helper=m+1
        val month=helper.toString()

        if(loggedIn==1){

            val userName=this.intent.getStringExtra(NAME).toString()
            val msg= "Welcome $userName!"
            val toast=Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG)
            toast.show()
        }
        else if(back==1){

        }
        else{
            val msg= "$year.$month.$day $hour:$minute"
            val toast=Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG)
            toast.show()
        }

        val car1 =findViewById<ImageButton>(R.id.car1)
        val car2 =findViewById<ImageButton>(R.id.car2)
        val car3 =findViewById<ImageButton>(R.id.car3)
        val car4 =findViewById<ImageButton>(R.id.car4)
        val car5 =findViewById<ImageButton>(R.id.car5)
        val car6 =findViewById<ImageButton>(R.id.car6)


        //Refresh
        val refresh = findViewById<Button>(R.id.refresh)
        refresh.setOnClickListener{
            //TODO: Communicate with MQTT!!!


            //TODO: Make changes accordingly
            //ez már itt a logika az IF argumentumába kéne betenni azt, hogy bent van e az autó az adott helyen vagy sem
            if(true){
                car1.setImageResource(R.drawable.greencar)
            }
            else{
                car1.setImageResource(R.drawable.redcar)
            }

            if(true){
                car2.setImageResource(R.drawable.greencar)
            }
            else{
                car2.setImageResource(R.drawable.redcar)
            }

            if(true){
                car3.setImageResource(R.drawable.greencar)
            }
            else{
                car3.setImageResource(R.drawable.redcar)
            }

            if(true){
                car4.setImageResource(R.drawable.greencar)
            }
            else{
                car4.setImageResource(R.drawable.redcar)
            }

            if(true){
                car5.setImageResource(R.drawable.greencar)
            }
            else{
                car5.setImageResource(R.drawable.redcar)
            }

            if(true){
                car6.setImageResource(R.drawable.greencar)
            }
            else{
                car6.setImageResource(R.drawable.redcar)
            }


            
        //temp. "solution"
        //car1.setImageResource(R.drawable.greencar)
        }


        val day2=this.intent.getIntExtra(DAY,0)
        val month2=this.intent.getIntExtra(MONTH,0)
        val year2=this.intent.getIntExtra(YEAR,0)
        val hour2=this.intent.getIntExtra(HOUR,0)
        val minute2=this.intent.getIntExtra(MINUTE,0)

        val myReservation=findViewById<Button>(R.id.myReservation)
        myReservation.setOnClickListener(){
            val i = Intent(this, MyReservationActivity::class.java)
            i.putExtra(MyReservationActivity.NAME, username)
            i.putExtra(MyReservationActivity.PARKINGLOT, pLot)
            i.putExtra(MyReservationActivity.RESERVEDTRUE, resTrue)
            i.putExtra(MyReservationActivity.YEAR, year2)
            i.putExtra(MyReservationActivity.MONTH, month2)
            i.putExtra(MyReservationActivity.DAY, day2)
            i.putExtra(MyReservationActivity.HOUR, hour2)
            i.putExtra(MyReservationActivity.MINUTE, minute2)
            startActivity(i)
        }

        //Go to the reservation page
        val reserve =findViewById<Button>(R.id.reserve)
        reserve.setOnClickListener{
            val i=Intent(this, ReserveActivity::class.java)
            i.putExtra(ReserveActivity.NAME,username)
            startActivity(i)
        }

        car1.setOnClickListener{
            val i = Intent(this, SelectDateActivity::class.java)
            i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 1)
            i.putExtra(SelectDateActivity.NAME,username)
            startActivity(i)
        }

        car2.setOnClickListener{
            val i = Intent(this, SelectDateActivity::class.java)
            i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 2)
            i.putExtra(SelectDateActivity.NAME,username)
            startActivity(i)
        }

        car3.setOnClickListener{
            val i = Intent(this, SelectDateActivity::class.java)
            i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 3)
            i.putExtra(SelectDateActivity.NAME,username)
            startActivity(i)
        }

        car4.setOnClickListener{
            val i = Intent(this, SelectDateActivity::class.java)
            i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 4)
            i.putExtra(SelectDateActivity.NAME,username)
            startActivity(i)
        }

        car5.setOnClickListener{
            val i = Intent(this, SelectDateActivity::class.java)
            i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 5)
            i.putExtra(SelectDateActivity.NAME,username)
            startActivity(i)
        }

        car6.setOnClickListener{
            val i = Intent(this, SelectDateActivity::class.java)
            i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 6)
            i.putExtra(SelectDateActivity.NAME,username)
            startActivity(i)
        }


    }
}
