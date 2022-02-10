package fr.isen.pierre.zaremba.androiderestaurant

import android.annotation.SuppressLint
import android.app.usage.NetworkStats
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityDetailBinding.*
import fr.isen.pierre.zaremba.androiderestaurant.model.DataBucket
import fr.isen.pierre.zaremba.androiderestaurant.model.DataBucketItem
import java.io.File


class DetailActivity : AppCompatActivity() {

    private var nbTotalInBucket = 0
    lateinit var sharedPreferences: SharedPreferences
    var basket: DataBucket = DataBucket()
    var basketItem: DataBucketItem = DataBucketItem()
    var newBasket: DataBucket = DataBucket()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Affichage du panier dans la toolbar
        val id = item.getItemId()

        if (id == R.id.action_one) {
            //Toast.makeText(this, "Item One Clicked", Toast.LENGTH_LONG).show()
            val intent = Intent(this, BucketActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Enregistrement des preferences utilisateur
        sharedPreferences = getSharedPreferences("mySavedBucket",Context.MODE_PRIVATE)

        // récupération de l'objet plat
        val detailDish = intent.getSerializableExtra("dish") as DishModel
        basketItem.dish = detailDish

        if (detailDish.images.isNotEmpty() && detailDish.images[0].isNotEmpty()) {
            binding.defaultImagePager.isVisible = false
            binding.dishDetailPager.adapter = DishDetailAdapter(this, detailDish.images)
        }
        else {
            binding.defaultImagePager.isVisible = true
            binding.dishDetailPager.isVisible = false
        }

        // mise en place du titre du plat avec l'objet
        binding.detailtextView.text = detailDish.name_fr
        // récupération et affichage des ingrédients
        binding.textIngredient.text =
            detailDish.ingredients.joinToString(", ") { it -> "${it.name_fr}" }

        var nbInBucket: Int = 0

        getTotalPrice(0)

        binding.plusButton.setOnClickListener {
            if (nbInBucket < 10) {
                nbInBucket++
                getTotalPrice(nbInBucket)
            }

        }
        binding.minusButton.setOnClickListener {
            if (nbInBucket > 1) {
                nbInBucket--
                getTotalPrice(nbInBucket)
            }
        }

        binding.totalButton.setOnClickListener{

            // Affichage de l'ajout dans le panier
            val snack = Snackbar.make(it,"Add to bucket !",Snackbar.LENGTH_SHORT)
            snack.show()

            //Calcul du total des plats dans le panier
            nbTotalInBucket = sharedPreferences.getInt("nbTotalInBucket", nbTotalInBucket) + nbInBucket

            // CREATION TOAST pour affichage de la quantité dans le panier
            val text = nbTotalInBucket.toString()
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.setGravity(0, 400, -780)
            toast.show()

            val editeur = sharedPreferences.edit()
            editeur.putInt("nbTotalInBucket",  nbTotalInBucket)
            editeur.apply()

            /*création du fichier panier - Merci Patrick pour l'aide à la programmation objet !!!
            basketItem.quantity = nbInBucket
            basket.data.add(basketItem)
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            File(cacheDir.absolutePath, "bucket.json").writeText(gsonPretty.toJson(basket))*/
            getBasketFile(nbInBucket)

        }


    }

    // Fonction d'affichage du total
    private fun getTotalPrice (nbInBucket: Int) {
        val detailDish = intent.getSerializableExtra("dish") as DishModel
        binding.textBucket.text = nbInBucket.toString()
        var resultPrice: Float = nbInBucket * detailDish.prices[0].price.toFloat()
        binding.totalButton.text = "Total" + resultPrice.toString() + "€"
    }


    private fun getBasketFile (nbInBucket: Int){
        //verification du fichier bucket.json et lecture sinon ecriture
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val jsonFile = File(cacheDir.absolutePath, "bucket.json")
        basketItem.quantity = nbInBucket
        if (jsonFile.exists()){
            newBasket = gsonPretty.fromJson(jsonFile.readText(), DataBucket::class.java)
            newBasket.data.add(basketItem)
            jsonFile.writeText(gsonPretty.toJson(newBasket))
        }
        else {
            //création du fichier panier - Merci Patrick pour l'aide à la programmation objet !!!
            basket.data.add(basketItem)
            jsonFile.writeText(gsonPretty.toJson(basket))
        }
    }
}