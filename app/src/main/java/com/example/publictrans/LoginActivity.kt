package com.example.publictrans

//import android.widget.Toast
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Boolean.FALSE


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try{
            Thread.sleep(1000)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }

        setTheme(R.style.Theme_PublicTrans)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin= findViewById<Button>(R.id.btnLogin)
        val editName = findViewById<TextView>(R.id.editName)
        btnLogin.setOnClickListener{
            if(editName.text.toString().isEmpty()){
                editName.requestFocus()
                editName.error="Please enter your name!"
            }
            else {
                val logInName = editName.text.toString()
                val i = Intent(this@LoginActivity, ParkoloActivity::class.java)
                i.putExtra(ParkoloActivity.NAME, logInName,)
                i.putExtra(ParkoloActivity.LOGGED_IN, 1)
                i.putExtra(ParkoloActivity.RESERVEDTRUE, 0)
                i.putExtra(ParkoloActivity.BACK, 0)
                startActivity(i)
            }
        }



    }


}