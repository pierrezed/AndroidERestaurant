package fr.isen.pierre.zaremba.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.jar.Attributes

data class DishResult (val data: List<CategoryModel>): Serializable

data class CategoryModel (val name_fr: String, val items: List<DishModel>): Serializable

data class DishModel (val name_fr: String ="", @SerializedName (value = "images") val images: List<String> = arrayListOf(), val ingredients: List<Ingredient> = arrayListOf(), val prices: List<Price> = arrayListOf()): Serializable {
    fun getFirstPicture() = if(images[0].isNotEmpty()) images[0] else null
    fun getFormattedPrice() = prices[0].price + "€"
}

data class Price (val price: String): Serializable

data class Ingredient (val name_fr: String) : Serializable