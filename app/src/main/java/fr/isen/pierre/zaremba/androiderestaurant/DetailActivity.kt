package fr.isen.pierre.zaremba.androiderestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // récupération de l'objet plat
        val detailDish = intent.getSerializableExtra("dish") as DishModel
        // Récupération d'une simple image
        Picasso.get()
            .load(detailDish.getFirstPicture())
            .error(R.drawable.resto)
            .placeholder(R.drawable.resto)
            .into(binding.imageDetailView)


        // mise en place du titre du plat avec l'objet
        binding.detailtextView.text = detailDish.name_fr
        // récupération et affichage des ingrédients
        binding.textIngredient.text =
            detailDish.ingredients.joinToString(", ") { it -> "${it.name_fr}" }

        var nbInBucket: Int = 1

        binding.plusButton.setOnClickListener {
            if (nbInBucket >= 10) {
               return@setOnClickListener
            }
            else {
                nbInBucket++
                binding.textBucket.text = nbInBucket.toString()
                var resultPrice: Float = nbInBucket * detailDish.prices[0].price.toString().toFloat()
                binding.bucketButton.text = "Total " + resultPrice.toString() + "€"
            }

        }
        binding.minusButton.setOnClickListener {
            if (nbInBucket <= 0) {
                return@setOnClickListener
            }
            else {
                nbInBucket--
                binding.textBucket.text = nbInBucket.toString()
                var resultPrice: Float = nbInBucket * detailDish.prices[0].price.toString().toFloat()
                binding.bucketButton.text = "Total" + resultPrice.toString() + "€"
            }
        }

        binding.bucketButton.setOnClickListener{
            /*CREATION TOAST
            val text = "Add to Bucket"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()*/
            val snack = Snackbar.make(it,"Add to bucket !",Snackbar.LENGTH_SHORT)
            snack.show()

        }
    }
}