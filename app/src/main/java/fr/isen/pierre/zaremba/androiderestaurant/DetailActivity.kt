package fr.isen.pierre.zaremba.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityDetailBinding
import org.json.JSONObject


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /*binding.btn.setOnClickListener {
            val url = "http://test.api.catering.bluecodegames.com/menu"
            binding.textView.text = ""
            val params = HashMap<String, String>()
            params["id_shop"] = "1"
            val jsonObject = JSONObject(params as Map<*, *>?)
            val request = JsonObjectRequest(Request.Method.POST,url,jsonObject, { response ->
                try {
                    binding.textView.text = "Response: $response"
                } catch (e:Exception) {
                    binding.textView.text = "Exception: £e"
                }
            }, {
                binding.textView.text = "Volley error: $it"
            })*/

    }
}