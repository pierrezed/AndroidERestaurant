package fr.isen.pierre.zaremba.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityCommandBinding

class CommandActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommandBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}