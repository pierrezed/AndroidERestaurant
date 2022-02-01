package fr.isen.pierre.zaremba.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityMainCourseBinding
import org.json.JSONObject

class MainCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.maincourseTitle.text = intent.getStringExtra( "category_type")
        val categoryType = intent.getStringExtra( "category_type")

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val params = HashMap<String, String>()
        params["id_shop"] = "1"
        val jsonObject = JSONObject(params as Map<*, *>?)
        val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
            { response ->
                var gson = Gson()
                var dishResult = gson.fromJson(response.toString(), DishResult::class.java)

                displayDishes(dishResult.data.firstOrNull{ it.name_fr == categoryType}?.items ?: listOf())

                Log.d("", "$response")

        }, {
            Log.e("", "Volley error: $it")
        })
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        // Add the volley post request to the request queue
        Volley.newRequestQueue(this).add(request)

        }

    //private val intent: Intent = Intent(this, DetailActivity::class.java)
    //intent.putExtra("dishes", category)

    private fun displayDishes (dishResult: List<DishModel>) {
        // this creates a vertical layout Manager
        binding.maincourseRecycleView.layoutManager = LinearLayoutManager(this)

        // Setting the Adapter with the recyclerview
        binding.maincourseRecycleView.adapter = CustomAdapter(dishResult) { myDish ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish",myDish)
            startActivity(intent)

        }


    }

    }