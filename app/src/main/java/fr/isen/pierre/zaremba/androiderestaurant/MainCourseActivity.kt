package fr.isen.pierre.zaremba.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityMainCourseBinding

class MainCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.maincourseTitle.text = intent.getStringExtra( "category_type")

        // getting the recyclerview by its id
        // val recyclerview = findViewById<RecyclerView>(R.id.maincourseRecycleView)

        // this creates a vertical layout Manager
        binding.maincourseRecycleView.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        /*for (i in 1..20) {
            data.add(ItemsViewModel( "Item " + i))
        }*/
        data.add(ItemsViewModel( "Steack Tartare"))
        data.add(ItemsViewModel( "Pizza"))
        data.add(ItemsViewModel( "Poulet basquaise"))
        data.add(ItemsViewModel( "Spaguetti bolognaise"))
        data.add(ItemsViewModel( "Pot au feu"))
        data.add(ItemsViewModel( "Oeuf mimosa"))
        data.add(ItemsViewModel( "Burger du chef"))
        data.add(ItemsViewModel( "Aioli"))
        data.add(ItemsViewModel( "Speciale Patrick"))
        data.add(ItemsViewModel( "Methode Martinez"))
        data.add(ItemsViewModel( "Gratin dauphinois"))

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        binding.maincourseRecycleView.adapter = adapter
    }
}