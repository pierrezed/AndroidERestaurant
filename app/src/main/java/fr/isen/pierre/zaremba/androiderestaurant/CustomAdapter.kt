package fr.isen.pierre.zaremba.androiderestaurant


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CustomAdapter(private val mainCourses: List<DishModel>, val onItemsViewModelClicked: (DishModel) -> Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // Ne sera pas modifier pour le binding : trop de changement....
        val dishName: TextView = itemView.findViewById(R.id.cellmaincourseTextList)
        val dishPicture: ImageView = itemView.findViewById(R.id.dishPicture)
        val dishPrice: TextView = itemView.findViewById(R.id.dishPrice)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {



        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_maincourse, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dish = mainCourses[position]

        // sets the text to the textview from our itemHolder class
        holder.dishName.text = dish.name_fr
        Picasso.get()
            .load(mainCourses[position].getFirstPicture())
            .error(R.drawable.resto)
            .placeholder(R.drawable.resto)
            .into(holder.dishPicture)
        holder.dishPrice.text = mainCourses[position].getFormattedPrice()
        holder.itemView.setOnClickListener {
            onItemsViewModelClicked(mainCourses[position])
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mainCourses.size
    }


}




