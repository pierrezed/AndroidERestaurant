package fr.isen.pierre.zaremba.androiderestaurant.model

import fr.isen.pierre.zaremba.androiderestaurant.DishModel
import java.io.Serializable


data class DataBucket (var data: MutableList<DataBucketItem> = arrayListOf()): Serializable

data class DataBucketItem (var dish: DishModel = DishModel(), var quantity: Int = 0): Serializable

