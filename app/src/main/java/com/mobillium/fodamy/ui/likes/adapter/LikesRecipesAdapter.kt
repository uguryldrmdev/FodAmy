package com.mobillium.fodamy.ui.likes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.fodamy.BR
import com.mobillium.fodamy.data.responses.editorchoices.Recipe
import com.mobillium.fodamy.databinding.ItemLikesRecipesBinding

class LikesRecipesAdapter(private val urlListener: (recipe: Recipe) -> Unit) : RecyclerView.Adapter<LikesRecipesAdapter.MyViewHolder>(){

    var recipes = ArrayList<Recipe>()
    private lateinit var binding: ItemLikesRecipesBinding

    fun setCategoryListData(data:ArrayList<Recipe>){
        val diffUtil = LikeRecipesDiffUtil(this.recipes, data)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        this.recipes = data
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemLikesRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.setVariable(BR.recipe,recipes[position])
        holder.bind(recipes[position],urlListener)
    }

    class MyViewHolder(val binding: ItemLikesRecipesBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(recipe: Recipe, urlListener: (recipe: Recipe) -> Unit){
            binding.cardViewRecipe.setOnClickListener {
                urlListener.invoke(recipe)
            }
        }
    }
}
class LikeRecipesDiffUtil(
    private val oldList: ArrayList<Recipe>,
    private val newList: ArrayList<Recipe>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}