package fr.isen.pierre.zaremba.androiderestaurant


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityBasketBinding
import android.content.Intent
import com.google.gson.GsonBuilder
import fr.isen.pierre.zaremba.androiderestaurant.model.DataBucket
import fr.isen.pierre.zaremba.androiderestaurant.model.DataBucketItem
import java.io.File


class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding
    var basketItem: DataBucketItem = DataBucketItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonFile = File(cacheDir.absolutePath, "bucket.json")
        if (jsonFile.exists()) {
            val Basket = gson.fromJson(jsonFile.readText(), DataBucket::class.java)
            binding.basketRecycleView.adapter = BasketAdapter(Basket.data.toMutableList()) {
                Basket.data.remove(it)
                jsonFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(Basket))
            }

            val sharedPreferences = getSharedPreferences("mySavedBucket", Context.MODE_PRIVATE)
            var count = basketItem.quantity
            val editeur = sharedPreferences.edit()
            editeur.putInt("nbTotalInBucket", count)
            editeur.apply()
        }

        binding.basketRecycleView.layoutManager = LinearLayoutManager(this)

        binding.commandBasketButton.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)

        }


    }

}

