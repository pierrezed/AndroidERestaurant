package fr.isen.pierre.zaremba.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityHomeBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.starterTitle.setOnClickListener {
            changeActivity(getString(R.string.starters))
        }

        binding.maincourseTitle.setOnClickListener {
            changeActivity(getString(R.string.main_course))
        }

        binding.dessertTitle.setOnClickListener {
            changeActivity("desserts")
        }

        // CREATION TOAST
        // val btnclickme = findViewById<TextView>(R.id.text_click)
        // set on-click listener
        //btn_click_me.setOnClickListener {
        //  val text = "Entrees"
        //  val duration = Toast.LENGTH_SHORT
        //  val toast = Toast.makeText(applicationContext, text, duration)
        //  toast.show()

    }
    private fun changeActivity(category: String) {
        val intent = Intent(this, MainCourseActivity::class.java)
        intent.putExtra("category_type",category)
        // start your next activity
        startActivity(intent)
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.i("activity_home","onDestroy Called")
    }

}




