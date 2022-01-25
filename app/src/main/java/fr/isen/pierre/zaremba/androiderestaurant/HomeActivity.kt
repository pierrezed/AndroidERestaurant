package fr.isen.pierre.zaremba.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // CREATION TOAST
        // val btnclickme = findViewById<TextView>(R.id.text_click)
        // set on-click listener
        //btn_click_me.setOnClickListener {
        //  val text = "Entrees"
        //  val duration = Toast.LENGTH_SHORT
        //  val toast = Toast.makeText(applicationContext, text, duration)
        //  toast.show()

        val btnstarter = findViewById<TextView>(R.id.starters)
        val btnmaincourse = findViewById<TextView>(R.id.maincourse)

        btnstarter.setOnClickListener {
            val intent = Intent(this, StarterActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
        btnmaincourse.setOnClickListener {
            val intent = Intent(this, MainCourseActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i("activity_home","onDestroy Called")
    }

}




