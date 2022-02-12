package fr.isen.pierre.zaremba.androiderestaurant


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivitySigninBinding
import fr.isen.pierre.zaremba.androiderestaurant.model.ClientResult
import org.json.JSONObject

class SigninActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.connexionButton.setOnClickListener {
            // Vérification des champs vides
            if (binding.emailconnexionText.text.isEmpty()
                || binding.passwordconnexiontext.text.isEmpty()
            ) {
                // Taost avertissement
                val text = " Renseignez le login et le mot de passe"
                getActionToast(text)

            }

            // envoi de la requète de connexion
            val url = "http://test.api.catering.bluecodegames.com/user/login"
            val params = HashMap<String, String>()
            params["id_shop"] = "1"
            params["email"] = binding.emailconnexionText.text.toString()
            params["password"] = binding.passwordconnexiontext.text.toString()

            val jsonObject = JSONObject(params as Map<*, *>?)
            val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                { response ->
                    var gson = Gson()
                    var clientResult = gson.fromJson(response.toString(), ClientResult::class.java)
                    // condition pour avoir un login et MDP reconnu
                    if (response.toString().contains("data")) {
                        val intent = Intent(this, CommandActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        val text = "Login ou mot de passe incorrect"
                        getActionToast(text)
                    }
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

    }

    private fun getActionToast(text: String) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this, text, duration)
        toast.show()
    }

}