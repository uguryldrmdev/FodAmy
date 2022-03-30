package com.mobillium.fodamy.ui.homepage.adapter


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mobillium.fodamy.ui.homepage.editorchoices.EditorChoicesFragment
import com.mobillium.fodamy.ui.homepage.lastadded.LastAddedFragment

class HomepageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    val items = arrayListOf<Fragment>()
    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }

    fun addFragmentList(){
        items.add(EditorChoicesFragment())
        items.add(LastAddedFragment())
    }
}