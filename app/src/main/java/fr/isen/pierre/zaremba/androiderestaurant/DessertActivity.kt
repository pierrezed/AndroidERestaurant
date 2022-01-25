package fr.isen.pierre.zaremba.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityDessertBinding
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityMainCourseBinding

class DessertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDessertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDessertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dessertTitle.text = intent.getStringExtra( "category_type")
    }
}

