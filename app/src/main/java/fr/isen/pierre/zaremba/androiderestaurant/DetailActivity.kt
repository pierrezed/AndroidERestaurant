package fr.isen.pierre.zaremba.androiderestaurant

import android.annotation.SuppressLint
import android.app.usage.NetworkStats
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityDetailBinding.*


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.action_one) {
            Toast.makeText(this, "Item One Clicked", Toast.LENGTH_LONG).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val toolbar: Toolbar = findViewById(R.id.detailToolbar)
        //setSupportActionBar(toolbar)

        // récupération de l'objet plat
        val detailDish = intent.getSerializableExtra("dish") as DishModel

        if (detailDish.images.isNotEmpty() && detailDish.images[0].isNotEmpty()) {
            binding.defaultImagePager.isVisible = false
            binding.dishDetailPager.adapter = DishDetailAdapter(this, detailDish.images)
        }
        else {
            binding.defaultImagePager.isVisible = true
            binding.dishDetailPager.isVisible = false
        }

        /*Récupération d'une simple image
        Picasso.get()
            .load(detailDish.getFirstPicture())
            .error(R.drawable.resto)
            .placeholder(R.drawable.resto)
            .into(binding.imageDetailView)*/


        // mise en place du titre du plat avec l'objet
        binding.detailtextView.text = detailDish.name_fr
        // récupération et affichage des ingrédients
        binding.textIngredient.text =
            detailDish.ingredients.joinToString(", ") { it -> "${it.name_fr}" }

        var nbInBucket: Int = 1
        getTotalPrice(1)
        /*binding.textBucket.text = nbInBucket.toString()
        var resultPrice: Float = nbInBucket * detailDish.prices[0].price.toString().toFloat()
        binding.bucketButton.text = "Total " + resultPrice.toString() + "€"*/

        binding.plusButton.setOnClickListener {
            if (nbInBucket < 10) {
                nbInBucket++
                getTotalPrice(nbInBucket)
                /*binding.textBucket.text = nbInBucket.toString()
                var resultPrice: Float = nbInBucket * detailDish.prices[0].price.toString().toFloat()
                binding.bucketButton.text = "Total " + resultPrice.toString() + "€"*/
            }

        }
        binding.minusButton.setOnClickListener {
            if (nbInBucket > 1) {
                nbInBucket--
                getTotalPrice(nbInBucket)
                /*binding.textBucket.text = nbInBucket.toString()
                var resultPrice: Float = nbInBucket * detailDish.prices[0].price.toString().toFloat()
                binding.bucketButton.text = "Total" + resultPrice.toString() + "€"*/
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

            val saveBucketPreference = getSharedPreferences("mySavedBucket",Context.MODE_PRIVATE)
            val editeur = saveBucketPreference.edit()
            editeur.putInt("nbInBucket",  nbInBucket)
            editeur.apply()

            /*val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)*/

        }
    }
    // Fonction d'affichage du total
    private fun getTotalPrice (nbInBucket: Int) {
        val detailDish = intent.getSerializableExtra("dish") as DishModel
        binding.textBucket.text = nbInBucket.toString()
        var resultPrice: Float = nbInBucket * detailDish.prices[0].price.toString().toFloat()
        binding.bucketButton.text = "Total" + resultPrice.toString() + "€"
    }
}