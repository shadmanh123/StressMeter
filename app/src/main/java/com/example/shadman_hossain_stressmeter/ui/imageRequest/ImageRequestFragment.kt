package com.example.shadman_hossain_stressmeter.ui.imageRequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shadman_hossain_stressmeter.databinding.FragmentImageRequestBinding

class ImageRequestFragment : Fragment() {

    private val imageFileNames = arrayOf(
        "psm_alarm_clock.png", "psm_alarm_clock2.png", "psm_angry_face.png", "psm_anxious.png",
        "psm_baby_sleeping.png", "psm_bar.png", "psm_barbed_wire2.png", "psm_beach3.png",
        "psm_bird3.png", "psm_blue_drop.png", "psm_cat.png", "psm_clutter.png", "psm_clutter3.png",
        "psm_dog_sleeping.png", "psm_exam4.png", "psm_gambling4.png", "psm_headache.png", "psm_headache2.png",
        "psm_hiking3.png", "psm_kettle.png", "psm_lake3.png", "psm_lawn_chairs3.png", "psm_lonely.png",
        "psm_lonely2.png", "psm_mountains11.png", "psm_neutral_child.png", "psm_neutral_person2.png",
        "psm_peaceful_person.png", "psm_puppy.png", "psm_puppy3.png", "psm_reading_in_bed2.png", "psm_running3.png",
        "psm_running4.png", "psm_sticky_notes2.png", "psm_stressed_cat.png", "psm_stressed_person.png",
        "psm_stressed_person12.png", "psm_stressed_person3.png", "psm_stressed_person4.png", "psm_stressed_person6.png",
        "psm_stressed_person7.png", "psm_stressed_person8.png", "psm_talking_on_phone2.png", "psm_to_do_list.png",
        "psm_to_do_list3.png", "psm_wine3.png", "psm_work4.png", "psm_yoga4.png"
    )
    private lateinit var imageResourceId:Array<Int>
    private var _binding: FragmentImageRequestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var gridView: GridView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val imageRequestViewModel =
            ViewModelProvider(this).get(ImageRequestViewModel::class.java)
        _binding = FragmentImageRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root
        imageResourceId = imageFileNames.map { filename ->
            resources.getIdentifier(filename, "drawable", requireContext().packageName)
        }.toTypedArray()
        val initialSelection = imageResourceId.take(16).toSet()
        imageRequestViewModel.addSelectedImages(initialSelection)

        imageRequestViewModel.addSelectedImages(initialSelection)
        imageAdapter = ImageAdapter(requireContext(),imageResourceId)
        gridView = binding.imageGridView
        gridView.adapter = imageAdapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}