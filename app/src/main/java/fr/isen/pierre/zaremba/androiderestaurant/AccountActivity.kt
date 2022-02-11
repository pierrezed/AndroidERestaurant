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
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityAccountBinding
import fr.isen.pierre.zaremba.androiderestaurant.model.ClientData
import fr.isen.pierre.zaremba.androiderestaurant.model.ClientResult
import org.json.JSONObject


class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    /*fun isValidString(str: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonaccountRegister.setOnClickListener {

            // vérification du remplissage des champs
            if (binding.accountFirstNameText.text.isEmpty()
                || binding.accountNameText.text.isEmpty()
                || binding.accountPostalAddress1.text.isEmpty()
                || binding.accountemail.text.isEmpty()
                || binding.accountPassword.text.isEmpty()
            ) {
                //CREATION TOAST
                val text = "Un champ est vide"
                getActionToast(text)
            }

            //envoi de la requète pour récupérer l'Id Client
            val url = "http://test.api.catering.bluecodegames.com/user/register"
            val params = HashMap<String, String>()
            params["id_shop"] = "1"
            params["firstname"] = binding.accountFirstNameText.text.toString()
            params["lastname"] = binding.accountNameText.text.toString()
            params["address"] = binding.accountPostalAddress1.text.toString()
            params["email"] = binding.accountemail.text.toString()
            params["password"] = binding.accountPassword.text.toString()

            val jsonObject = JSONObject(params as Map<*, *>?)
            val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                { response ->
                    var gson = Gson()
                    var clientResult = gson.fromJson(response.toString(), ClientResult::class.java)

                    if (clientResult.data.id.isNotEmpty() &&
                        clientResult.code.toInt() == 200) {
                        val intent = Intent(this, CommandActivity::class.java)
                        startActivity(intent)
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
        binding.signIn.setOnClickListener {
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
    }


    private fun getActionToast(text: String) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this, text, duration)
        toast.show()
    }
}