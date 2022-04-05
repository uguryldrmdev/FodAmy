package com.mobillium.fodamy.ui.homepage.lastadded.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.fodamy.BR
import com.mobillium.fodamy.data.responses.editorchoices.Recipe
import com.mobillium.fodamy.databinding.ItemEditorChoicesBinding

class LastAddedAdapterPaging(private val onRecipeSelectListener: (recipe: Recipe,isUserClicked: Boolean ) -> Unit)
    : PagingDataAdapter<Recipe, LastAddedAdapterPaging.MyViewHolder>(DIFF_UTIL) {

    companion object{
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Recipe>(){
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.recipe,getItem(position))
        holder.bind(getItem(position),onRecipeSelectListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemEditorChoicesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder(val viewDataBinding: ItemEditorChoicesBinding): RecyclerView.ViewHolder(viewDataBinding.root){

        fun bind(recipe: Recipe?, onRecipeSelectListener: (recipe: Recipe, isUserClicked: Boolean) -> Unit) {

            viewDataBinding.clRecipeInfo.setOnClickListener {
                if (recipe != null) {
                    onRecipeSelectListener.invoke(recipe,false)
                }
            }
            viewDataBinding.clUserInfo.setOnClickListener {
                if (recipe != null) {
                    onRecipeSelectListener.invoke(recipe,true)
                }
            }
        }
    }
}