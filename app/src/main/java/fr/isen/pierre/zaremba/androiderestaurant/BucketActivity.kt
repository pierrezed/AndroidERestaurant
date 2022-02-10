package fr.isen.pierre.zaremba.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityBucketBinding
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityCommandBinding

class BucketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBucketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBucketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.commandButton.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)

        }

    }
}