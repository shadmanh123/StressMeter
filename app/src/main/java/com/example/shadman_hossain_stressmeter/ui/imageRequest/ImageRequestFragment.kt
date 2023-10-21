package com.example.shadman_hossain_stressmeter.ui.imageRequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shadman_hossain_stressmeter.databinding.FragmentImageRequestBinding

class ImageRequestFragment : Fragment() {

//    private val imageResourceId = arrayOf(
//        R.drawable.psm
//    )
    private var _binding: FragmentImageRequestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val imageRequestViewModel =
            ViewModelProvider(this).get(ImageRequestViewModel::class.java)

        _binding = FragmentImageRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}