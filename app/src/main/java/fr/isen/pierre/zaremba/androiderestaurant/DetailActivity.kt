package fr.isen.pierre.zaremba.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityMainCourseBinding
import org.json.JSONObject
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityDetailBinding as ActivityDetailBinding1


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding1.inflate(layoutInflater)
        setContentView(binding.root)

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