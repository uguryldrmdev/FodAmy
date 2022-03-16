package com.mobillium.fodamy.ui.main.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobillium.fodamy.data.preferences.MyPreferences
import com.mobillium.fodamy.databinding.FragmentHomepageBinding
import com.mobillium.fodamy.ui.auth.AuthActivity
import com.mobillium.fodamy.ui.startNewActivity


class HomepageFragment : Fragment() {

    private lateinit var binding: FragmentHomepageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomepageBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (MyPreferences(requireContext()).token == ""){
            binding.button.text = "Giriş Yap"
        }
        else{
            binding.button.text = "Çıkış Yap"
        }

        binding.button.setOnClickListener {
            requireActivity().startNewActivity(AuthActivity::class.java)
            MyPreferences(requireContext()).token = ""
        }

    }

}