package fr.isen.pierre.zaremba.androiderestaurant


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.pierre.zaremba.androiderestaurant.databinding.CellBasketBinding

import fr.isen.pierre.zaremba.androiderestaurant.model.DataBucketItem



class BasketAdapter(private val basketItems: MutableList<DataBucketItem>, val deleteItemListener: (DataBucketItem) -> Unit) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>(){

    class BasketViewHolder(binding: CellBasketBinding) : RecyclerView.ViewHolder(binding.root) {

        val dishPictureBasket = binding.dishPictureBasket
        val itemBasketText = binding.itemBasketText
        val itemQuantity = binding.itemQuantity
        val deleteItem = binding.trashBasketButton

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {

        val binding = CellBasketBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {

        holder.itemBasketText.text = basketItems[position].dish.name_fr
        holder.itemQuantity.text = basketItems[position].quantity.toString()
        Picasso.get()
            .load(basketItems[position].dish.getFirstPicture())
            .error(R.drawable.resto)
            .placeholder(R.drawable.resto)
            .into(holder.dishPictureBasket)
        holder.deleteItem.setOnClickListener {
            basketItems.remove(basketItems[position])
            deleteItemListener(basketItems[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return basketItems.size
    }

}



