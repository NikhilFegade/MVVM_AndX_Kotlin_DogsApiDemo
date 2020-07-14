package com.softnik.mvvm_andx_kotlin_dogsapidemo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.softnik.mvvm_andx_kotlin_dogsapidemo.R
import com.softnik.mvvm_andx_kotlin_dogsapidemo.model.DogBreed
import com.softnik.mvvm_andx_kotlin_dogsapidemo.util.getProgressDrawable
import com.softnik.mvvm_andx_kotlin_dogsapidemo.util.loadImage
import kotlinx.android.synthetic.main.item_dogs.view.*

class DogsListAdapter(val dogList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dogs, parent, false)



        return DogViewHolder(view)

    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.tvDogName.text = dogList[position].dogBreed
        holder.view.tvLifeSpan.text = dogList[position].lifeSpan
        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
        }
        holder.view.ivDog.loadImage(dogList[position].imageUrl , getProgressDrawable(holder.view.ivDog.context))

    }

    fun updateDogsList(newDogsList: List<DogBreed>) {
        dogList.clear()
        dogList.addAll(newDogsList)
        notifyDataSetChanged()

    }


    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view) {


    }


}