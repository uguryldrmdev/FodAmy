package com.mobillium.fodamy.ui.splash.intro

import  android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.mobillium.fodamy.R
import com.mobillium.fodamy.databinding.FragmentIntroBinding
import com.mobillium.fodamy.data.model.IntroSlideModel
import com.mobillium.fodamy.data.preferences.MyPreferences
import com.mobillium.fodamy.ui.main.MainActivity
import com.mobillium.fodamy.ui.splash.intro.adapter.IntroSliderAdapter
import com.mobillium.fodamy.ui.startNewActivity
import kotlinx.coroutines.launch


class IntroFragment : Fragment() {

    private lateinit var mItemsAdapter: IntroSliderAdapter
    private lateinit var binding: FragmentIntroBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater,container,false)
        return binding.root
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
            requireActivity().startNewActivity(MainActivity::class.java)
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