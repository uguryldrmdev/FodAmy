package com.mobillium.fodamy.ui.splash.intro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.fodamy.databinding.SlideItemIntroBinding
import com.mobillium.fodamy.data.model.IntroSlideModel
import kotlinx.android.synthetic.main.slide_item_intro.view.*

class IntroSliderAdapter :
    RecyclerView.Adapter<IntroSliderAdapter.MyViewHolder>(){

    private var items = ArrayList<IntroSlideModel>()
    private lateinit var binding: SlideItemIntroBinding

    fun setItemsData(data:ArrayList<IntroSlideModel>){
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = SlideItemIntroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }
    class MyViewHolder(private val binding: SlideItemIntroBinding): RecyclerView.ViewHolder(binding.root){

        var itemTitle: TextView = binding.textViewSlideTitle
        var itemDescription: TextView = binding.textViewSlideDesc
        var itemImage: ImageView = binding.imageViewSlideIcon
        fun bind(item: IntroSlideModel) {
            itemTitle.text = item.title
            itemDescription.text = item.description
            itemImage.setImageResource(item.icon)
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
}