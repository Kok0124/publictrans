package com.example.publictrans

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.util.*


class SelectDateActivity : AppCompatActivity() {

    private lateinit var mqttClient: MqttAndroidClient

    companion object{
        const val KEY_PARKING_LOT="KEY_PARKING_LOT"
        const val NAME="NAME"

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_date)


        val reservedDate=findViewById<TextView>(R.id.ReserveDate)
        val calendar = GregorianCalendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_MONTH)
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val minute=calendar.get(Calendar.MINUTE)

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


        reservedDate.text= "$yearSt.$monthSt.$daySt. $hourSt:$minuteSt"




        val parkingLot=this.intent.getIntExtra(KEY_PARKING_LOT, -1)
        val textView3= findViewById<TextView>(R.id.textView3)
        textView3.text=getWelcomeString(parkingLot)

        val username=this.intent.getStringExtra(NAME).toString()

        val reserveBtn=findViewById<Button>(R.id.reserveBtn)
        reserveBtn.setOnClickListener(){




           // val i = Intent(this, ParkoloActivity::class.java)
           // i.putExtra(ParkoloActivity.LOGGED_IN, 0)
           // i.putExtra(ParkoloActivity.YEAR,year)
           // i.putExtra(ParkoloActivity.MONTH,month)
           // i.putExtra(ParkoloActivity.DAY,day)
           // i.putExtra(ParkoloActivity.MINUTE,minute)
           // i.putExtra(ParkoloActivity.HOUR,hour)

           // i.putExtra(ParkoloActivity.PARKINGLOT,parkingLot)
           // i.putExtra(ParkoloActivity.RESERVEDTRUE,1)
           // i.putExtra(ParkoloActivity.NAME, username)
           // startActivity(i)
            finish()
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

    // a publish function, ami arra kell majd ha lefoglal vki egy helyet akkor felülirjuk a szenzoradatot.
    private fun publish(topic: String, msg: String, qos: Int = 1, retained: Boolean = false) {
        try {
            val message = MqttMessage()
            message.payload = msg.toByteArray()
            message.qos = qos
            message.isRetained = retained
            mqttClient.publish(topic, message, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d(ParkoloActivity.TAG, "$msg published to $topic")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d(ParkoloActivity.TAG, "Failed to publish $msg to $topic")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

}