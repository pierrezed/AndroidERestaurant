package fr.isen.pierre.zaremba.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.pierre.zaremba.androiderestaurant.model.DataBucketItem
import android.content.Intent


class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.basketRecycleView.layoutManager = LinearLayoutManager(this)

        binding.basketRecycleView.adapter = BasketAdapter(DataBucketItem)

        // a remplacer par l'accès au fcichier bucket json voir dans detailActivity
        // récupération de l'objet bucket.kt
        //val detailDish = intent.getSerializableExtra("dish") as DishModel
        //basketItem.dish = detailDish



        binding.commandBasketButton.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
            // Question : ce n'est pas le départ d'une nouvelle activité

        }

    }
}