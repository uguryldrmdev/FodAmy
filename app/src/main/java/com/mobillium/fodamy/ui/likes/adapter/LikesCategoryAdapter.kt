package com.mobillium.fodamy.ui.likes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.fodamy.BR
import com.mobillium.fodamy.R
import com.mobillium.fodamy.data.responses.likes.Category
import com.mobillium.fodamy.databinding.ItemLikesCategoriesBinding
import com.mobillium.fodamy.ext.setDivider

class LikesCategoryAdapter( private val urlListener: (category: Category) -> Unit) : RecyclerView.Adapter<LikesCategoryAdapter.MyViewHolder>(){

    var categories = ArrayList<Category>()
    private lateinit var binding: ItemLikesCategoriesBinding

    fun setCategoryListData(data:ArrayList<Category>){
        val diffUtil = CategoriesDiffUtil(this.categories, data)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        this.categories = data
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemLikesCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.setVariable(BR.category,categories[position])
        holder.bind(categories[position],urlListener,)
    }

    class MyViewHolder(val binding: ItemLikesCategoriesBinding): RecyclerView.ViewHolder(binding.root){

        private lateinit var mLikeRecipeAdapter: LikesRecipesAdapter
        fun bind(category: Category,urlListener: (category: Category) -> Unit){
            binding.textViewSeeAll.setOnClickListener {
                urlListener.invoke(category)
            }
            binding.recycleViewRecipes.apply {
                val myLinearLayoutManager = object : LinearLayoutManager(context,HORIZONTAL,false){}
                layoutManager = myLinearLayoutManager
                LikesRecipesAdapter{ recipe ->
                    //Değişecek
                    Toast.makeText(context,recipe.title, Toast.LENGTH_LONG).show()
                }.also { mLikeRecipeAdapter = it }
                adapter = mLikeRecipeAdapter
            }
            mLikeRecipeAdapter.setCategoryListData(category.recipes)
        }


    }
}
class CategoriesDiffUtil(
    private val oldList: ArrayList<Category>,
    private val newList: ArrayList<Category>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}