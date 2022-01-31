package fr.isen.pierre.zaremba.androiderestaurant

data class DishResult (
    val data: List<CategoryModel>
)
data class CategoryModel (val name_fr: String, val items: List<DishModel>)
data class DishModel (val name_fr: String)

