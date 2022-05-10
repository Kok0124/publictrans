package com.example.publictrans

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import android.util.Log
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import java.util.*

class ParkoloActivity : AppCompatActivity() {
    //@SuppressLint("UseCompatLoadingForDrawables")

    private lateinit var mqttClient: MqttAndroidClient
    private var MQTT_MSG : String = "DeafultValue"
    private var RESERVEDTRUE : Int = 0
    private var PARKINGLOT : Int = -1

    private lateinit var car1: ImageButton
    private lateinit var car2: ImageButton


    companion object{
        const val LOGGED_IN="LOGGED_IN"
        const val NAME="NAME"
        const val YEAR="YEAR"
        const val MONTH="MONTH"
        const val DAY="DAY"
        const val HOUR="HOUR"
        const val MINUTE="MINUTE"
        //const val PARKINGLOT="PARKING_LOT"
        //const val RESERVEDTRUE="RESERVEDTRUE"
        const val BACK="BACK"
        const val TAG = "AndroidMqttClient"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parkolo)

        connect(this)


        val loggedIn=this.intent.getIntExtra(LOGGED_IN, -1)
        val back=this.intent.getIntExtra(BACK, -1)
        val username=this.intent.getStringExtra(NAME) //.toString()
        //val pLot=this.intent.getIntExtra(PARKINGLOT, -1)
        //val resTrue=this.intent.getIntExtra(RESERVEDTRUE, -1)

        val year=this.intent.getIntExtra(YEAR,0)
        var day=this.intent.getIntExtra(DAY,0)
        var month=this.intent.getIntExtra(MONTH,0)
        month += 1
        var hour=this.intent.getIntExtra(HOUR,0)
        var minute=this.intent.getIntExtra(MINUTE,0)




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


        if(loggedIn==1){

            val userName=this.intent.getStringExtra(NAME).toString()
            val msg= "Welcome $userName!"
            val toast=Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT)
            toast.show()
        }
        else if(back==1){

        }
        else{
            val msg= "$yearSt.$monthSt.$daySt. $hourSt:$minuteSt"
            val toast=Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG)
            toast.show()
        }

        car1 =findViewById<ImageButton>(R.id.car1)
        car2 =findViewById<ImageButton>(R.id.car2)
        val car3 =findViewById<ImageButton>(R.id.car3)
        val car4 =findViewById<ImageButton>(R.id.car4)
        val car5 =findViewById<ImageButton>(R.id.car5)
        val car6 =findViewById<ImageButton>(R.id.car6)

        refreshCar1("1_0")
        refreshCar1("2_0")


        val day2=this.intent.getIntExtra(DAY,0)
        val month2=this.intent.getIntExtra(MONTH,0)
        val year2=this.intent.getIntExtra(YEAR,0)
        val hour2=this.intent.getIntExtra(HOUR,0)
        val minute2=this.intent.getIntExtra(MINUTE,0)

        val myReservation=findViewById<Button>(R.id.myReservation)
        myReservation.setOnClickListener(){
            val i = Intent(this, MyReservationActivity::class.java)
            i.putExtra(MyReservationActivity.NAME, username)
            i.putExtra(MyReservationActivity.PARKINGLOT, PARKINGLOT)
            i.putExtra(MyReservationActivity.RESERVEDTRUE, RESERVEDTRUE)
            i.putExtra(MyReservationActivity.YEAR, Calendar.getInstance().get(Calendar.YEAR))
            i.putExtra(MyReservationActivity.MONTH, Calendar.getInstance().get(Calendar.MONTH))
            i.putExtra(MyReservationActivity.DAY, Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
            i.putExtra(MyReservationActivity.HOUR, Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
            i.putExtra(MyReservationActivity.MINUTE, Calendar.getInstance().get(Calendar.MINUTE))
            startActivity(i)
        }


        car1.setOnClickListener {
            var carImage1 = car1.tag
            if (carImage1 == "Green") {

                val i = Intent(this, SelectDateActivity::class.java)
                i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 1)
                i.putExtra(SelectDateActivity.NAME, username)

                startActivity(i)

                PARKINGLOT = 1
                RESERVEDTRUE = 1

                val msg= "1_1" //foglaló üzenet
                val topic="testtopic/tesztelem"
                publish(topic,msg)

                val timer = object : CountDownTimer(30000, 1000) {
                    override fun onTick(p0: Long) {

                    }


                    override fun onFinish() {
                        refreshCar1("1_0")
                    }
                }.start()


            }
        }

        car2.setOnClickListener{
            var carImage2 = car2.tag
            if (carImage2 == "Green") {
                val i = Intent(this, SelectDateActivity::class.java)
                i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 2)
                i.putExtra(SelectDateActivity.NAME,username)
                startActivity(i)

                PARKINGLOT = 2
                RESERVEDTRUE = 1

                val msg= "2_1" //foglaló üzenet
                val topic="testtopic/tesztelem"
                publish(topic,msg)

                val timer = object : CountDownTimer(30000, 1000) {
                    override fun onTick(p0: Long) {

                    }


                    override fun onFinish() {
                        refreshCar1("2_0")
                    }
                }.start()

            }
        }

        car3.setOnClickListener{
            var carImage3 = car3.tag
            if (carImage3 == "Green") {
                val i = Intent(this, SelectDateActivity::class.java)
                i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 3)
                i.putExtra(SelectDateActivity.NAME, username)
                startActivity(i)
            }
        }

        car4.setOnClickListener{
            var carImage4 = car4.tag
            if (carImage4 == "Green") {
                val i = Intent(this, SelectDateActivity::class.java)
                i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 4)
                i.putExtra(SelectDateActivity.NAME, username)
                startActivity(i)
            }
        }

        car5.setOnClickListener{
            var carImage5 = car5.tag
            if (carImage5 == "Green") {
                val i = Intent(this, SelectDateActivity::class.java)
                i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 5)
                i.putExtra(SelectDateActivity.NAME, username)
                startActivity(i)
            }
        }

        car6.setOnClickListener{
            var carImage6 = car6.tag
            if (carImage6 == "Green") {
                val i = Intent(this, SelectDateActivity::class.java)
                i.putExtra(SelectDateActivity.KEY_PARKING_LOT, 6)
                i.putExtra(SelectDateActivity.NAME, username)
                startActivity(i)
            }
        }




    }




    private fun refreshCar1(str: String) {
        when (str) {
            "1_0" -> {
                car1.setImageResource(R.drawable.greencar)
                car1.tag = "Green"
            }
            "1_1" -> {
                car1.setImageResource(R.drawable.redcar)
                car1.tag = "Red"
            }
            "2_0" -> {
                car2.setImageResource(R.drawable.greencar)
                car2.tag = "Green"
            }
            "2_1" -> {
                car2.setImageResource(R.drawable.redcar)
                car2.tag = "Green"
            }
            else -> {
                return;
            }
        }
    }

    private fun connect(context: Context) {
        val serverURI = "tcp://broker.hivemq.com:1883"
        mqttClient = MqttAndroidClient(context, serverURI, "kotlin_client")
        mqttClient.setCallback(object : MqttCallback {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                Log.d(TAG, "Receive message: ${message.toString()} from topic: $topic")
                MQTT_MSG = message.toString()
                //finish()
                //startActivity(getIntent());
                refreshCar1(MQTT_MSG)
            }

            override fun connectionLost(cause: Throwable?) {
                Log.d(TAG, "Connection lost ${cause.toString()}")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {

            }
        })
        val options = MqttConnectOptions()
        try {
            mqttClient.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d(TAG, "Connection success")
                    subscribe("testtopic/tesztelem")

                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d(TAG, "Connection failure")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
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
                    Log.d(TAG, "$msg published to $topic")
                    refreshCar1(msg)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d(TAG, "Failed to publish $msg to $topic")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    // Feliratkozas a megfelelo topicra, a szenzor adatokat kezelni.
    private fun subscribe(topic: String, qos: Int = 1) {
        try {
            mqttClient.subscribe(topic, qos, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d(TAG, "Subscribed to $topic")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d(TAG, "Failed to subscribe $topic")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    private fun receiveMessages() {
        mqttClient.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable) {
                //connectionStatus = false
                // Give your callback on failure here
            }override fun messageArrived(topic: String, message: MqttMessage) {
                try {
                    val data = String(message.payload, charset("UTF-8"))
                    // Give your callback on message received here
                } catch (e: Exception) {
                    // Give your callback on error here
                }
            }override fun deliveryComplete(token: IMqttDeliveryToken) {
                // Acknowledgement on delivery complete
            }
        })

    }

}
