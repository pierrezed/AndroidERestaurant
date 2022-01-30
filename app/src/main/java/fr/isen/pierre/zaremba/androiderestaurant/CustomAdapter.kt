package fr.isen.pierre.zaremba.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.pierre.zaremba.androiderestaurant.databinding.ActivityMainCourseBinding

class CustomAdapter(private val mainCourses: List<ItemsViewModel>, val onItemsViewModelClicked: (ItemsViewModel) -> Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // Ne sera pas modifier pour le binding : trop de changement....
        val textView: TextView = itemView.findViewById(R.id.cellmaincourseTextList)

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

        val ItemsViewModel = mainCourses[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text
        holder.itemView.setOnClickListener {
            onItemsViewModelClicked(mainCourses[position])
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mainCourses.size
    }


}

class ActivityAdapterCuston {

}

class ActivityAdpterCuston {

}
