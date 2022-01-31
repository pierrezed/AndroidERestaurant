package fr.isen.pierre.zaremba.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityMainCourseBinding
import org.json.JSONObject

class MainCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.maincourseTitle.text = intent.getStringExtra( "category_type")

        // this creates a vertical layout Manager
        binding.maincourseRecycleView.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        for (i in 1..20) {
            data.add(ItemsViewModel( "Item " + i))
        }
        data.add(ItemsViewModel( "Steack Tartare"))
        data.add(ItemsViewModel( "Pizza"))
        data.add(ItemsViewModel( "Poulet basquaise"))
        data.add(ItemsViewModel( "Spaguetti bolognaise"))
        data.add(ItemsViewModel( "Pot au feu"))
        data.add(ItemsViewModel( "Oeuf mimosa"))
        data.add(ItemsViewModel( "Burger du chef"))
        data.add(ItemsViewModel( "Aioli"))
        data.add(ItemsViewModel( "Plat du jour"))
        data.add(ItemsViewModel( "Methode Martinez"))
        data.add(ItemsViewModel( "Gratin dauphinois"))

        // Setting the Adapter with the recyclerview
        binding.maincourseRecycleView.adapter = CustomAdapter(data) {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

            val url = "http://test.api.catering.bluecodegames.com/menu"
            binding.detailtextView.text = ""
            val params = HashMap<String, String>()
            params["id_shop"] = "1"
            val jsonObject = JSONObject(params as Map<*, *>?)
            val request = JsonObjectRequest(Request.Method.POST, url, jsonObject, { response ->
                try {
                    binding.detailtextView.text = "Response: $response"
                } catch (e: Exception) {
                    binding.detailtextView.text = "Exception: £e"
                }
            }, {
                binding.detailtextView.text = "Volley error: $it"
            })
            request.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                // 0 means no retry
                0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            // Add the volley post request to the request queue
            VolleySingleton.getInstance(this).addToRequestQueue(request)



        }

    }