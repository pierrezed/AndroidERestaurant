package fr.isen.pierre.zaremba.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DishDetailAdapter(activity: AppCompatActivity, val images: List<String>): FragmentStateAdapter(activity){
    override fun getItemCount(): Int = images.size


    override fun createFragment(position: Int): Fragment {
        return DishImagesFragment.newInstance(images[position])
    }


}