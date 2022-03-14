package com.mobillium.fodamy.ui.intro

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentIntroBinding
import com.mobillium.fodamy.model.IntroSlideModel
import com.mobillium.fodamy.preferences.MyPreferences
import com.mobillium.fodamy.ui.intro.adapter.IntroSliderAdapter
import kotlinx.coroutines.launch


class IntroFragment : BaseFragment<ViewModel, FragmentIntroBinding>() {

    private lateinit var mItemsAdapter: IntroSliderAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        setClicks()
        setSlider()
    }

    private fun setClicks(){
        setNextButtonClick()
        setCloseIconClick()
    }

    private fun setNextButtonClick(){
        binding.buttonNext.setOnClickListener {
            if (binding.introSlider.currentItem + 1 < mItemsAdapter.itemCount){
                binding.introSlider.currentItem +=1
            }else{
                launchHomepage()
            }
        }
    }
    private fun setCloseIconClick(){
        binding.imageViewCloseIcon.setOnClickListener {
            launchHomepage()
        }
    }

    private fun launchHomepage(){
        lifecycleScope.launch {
            MyPreferences(requireContext()).isAppOpened = true
            findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToHomepageFragment())
        }
    }

    private fun setSlider(){
        initializeViewPager()
        setViewPagerData()
        initializeIndicators()
    }

    private fun initializeViewPager(){
        binding.introSlider.apply {

            IntroSliderAdapter().also {
                mItemsAdapter = it
            }
            adapter = mItemsAdapter
        }
    }

    private fun setViewPagerData(){
        mItemsAdapter.setItemsData(getIntroSlideItems())
    }

    private fun getIntroSlideItems(): ArrayList<IntroSlideModel>{
        val introItemList= ArrayList<IntroSlideModel>()
        val item1 = IntroSlideModel(getString(R.string.sliderItem1Title), getString(R.string.sliderItem1Desc),R.drawable.introimagefirst)
        val item2 = IntroSlideModel(getString(R.string.sliderItem2Title), getString(R.string.sliderItem2Desc),R.drawable.introimagesecond)
        val item3 = IntroSlideModel(getString(R.string.sliderItem3Title), getString(R.string.sliderItem3Desc),R.drawable.introimagethird)
        val item4 = IntroSlideModel(getString(R.string.sliderItem4Title), getString(R.string.sliderItem4Desc),R.drawable.introimagefourth)
        introItemList.add(item1)
        introItemList.add(item2)
        introItemList.add(item3)
        introItemList.add(item4)

        return introItemList
    }

    private fun initializeIndicators(){
        setupIndicators()
        setCurrentIndicator(0)
        binding.introSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                if (position+1 < mItemsAdapter.itemCount){
                    binding.buttonNext.text = getString(R.string.next)
                }else{
                    binding.buttonNext.text = getString(R.string.start)
                }
            }
        }
        )
    }

    private fun setupIndicators(){
        val indicators= arrayOfNulls<ImageView>(mItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(context?.applicationContext)
            indicators[i].apply {
                this?.let { setImageDrawableExt(it,R.drawable.indicator_inactive,context) }
                this?.layoutParams = layoutParams
            }
            binding.indicatorsContainer.addView(indicators[i])
        }
    }
    private fun setCurrentIndicator(index: Int){
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorsContainer[i] as ImageView
            if (i == index){
                setImageDrawableExt(imageView,R.drawable.indicator_active,context)
            }
            else {
                setImageDrawableExt(imageView,R.drawable.indicator_inactive,context)
            }
        }
    }



    override fun getViewModel(): Class<ViewModel> {
        TODO("Not yet implemented")
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentIntroBinding.inflate(inflater,container,false)

    companion object{
        fun setImageDrawableExt(imageView: ImageView,imageId:Int,context:Context?){
            imageView.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it.applicationContext,
                        imageId
                    )
                }
            )
        }
    }
}