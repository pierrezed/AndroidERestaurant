package fr.isen.pierre.zaremba.androiderestaurant.model

import java.io.Serializable

data class ClientResult (val data: ClientData, val code: String): Serializable

data class ClientData (val id: String): Serializable

