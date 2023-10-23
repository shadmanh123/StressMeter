package com.example.shadman_hossain_stressmeter.ui.imageRequest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shadman_hossain_stressmeter.R
import com.example.shadman_hossain_stressmeter.databinding.FragmentImageRequestBinding
import com.example.shadman_hossain_stressmeter.ImageResponse

class ImageRequestFragment : Fragment() {
    private lateinit var moreImagesButton: Button
    private lateinit var intent: Intent
    private var count = 0
    private var gridSelection: MutableSet<Int> = mutableSetOf()
    private lateinit var gridSelectionAsList: List<Int>
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
        val firstGrid = listOf(R.drawable.psm_alarm_clock,R.drawable.psm_headache2,
            R.drawable.psm_angry_face, R.drawable.psm_anxious, R.drawable.psm_baby_sleeping, R.drawable.psm_bar,
            R.drawable.psm_barbed_wire2, R.drawable.psm_beach3,R.drawable.psm_bird3,R.drawable.psm_blue_drop,
            R.drawable.psm_cat,R.drawable.psm_clutter,R.drawable.psm_neutral_person2,R.drawable.psm_dog_sleeping,
            R.drawable.psm_exam4,R.drawable.psm_gambling4)
        val secondGrid = listOf(R.drawable.psm_headache, R.drawable.psm_alarm_clock2,
            R.drawable.psm_hiking3, R.drawable.psm_kettle,R.drawable.psm_lake3, R.drawable.psm_lawn_chairs3,
            R.drawable.psm_lonely, R.drawable.psm_stressed_person, R.drawable.psm_mountains11,
            R.drawable.psm_neutral_child, R.drawable.psm_clutter3, R.drawable.psm_peaceful_person,
            R.drawable.psm_puppy, R.drawable.psm_to_do_list, R.drawable.psm_reading_in_bed2,
            R.drawable.psm_running3)
        val thirdGrid = listOf(R.drawable.psm_lonely2, R.drawable.psm_running4,
            R.drawable.psm_sticky_notes2, R.drawable.psm_stressed_cat,
            R.drawable.psm_stressed_person12, R.drawable.psm_stressed_person3,
            R.drawable.psm_stressed_person4, R.drawable.psm_stressed_person6,
            R.drawable.psm_stressed_person7, R.drawable.psm_stressed_person8,
            R.drawable.psm_talking_on_phone2, R.drawable.psm_puppy3,R.drawable.psm_to_do_list3,
            R.drawable.psm_wine3, R.drawable.psm_work4, R.drawable.psm_yoga4)

        val gridScores = listOf(6,8,14,16,5,7,13,15,2,4,10,12,1,3,9,11)
        gridSelection = firstGrid.toMutableSet() //first time app opens, this first grid is chosen
        gridSelectionAsList = firstGrid //grid selection as list is needed to determine name of drawable
        moreImagesButton = binding.root.findViewById(R.id.moreImagesButton)
        imageAdapter = ImageAdapter(requireContext(),firstGrid, gridScores)
        moreImagesButton.setOnClickListener {
            count++
            if (count == 0) {
                gridSelection = firstGrid.toMutableSet()
                gridSelectionAsList = firstGrid
                imageAdapter.updateImages(firstGrid)
            }
            else if (count == 1){
                gridSelection = secondGrid.toMutableSet()
                gridSelectionAsList = secondGrid
                imageAdapter.updateImages(secondGrid)
            }
            else{
                gridSelection = thirdGrid.toMutableSet()
                gridSelectionAsList = thirdGrid
                imageAdapter.updateImages(thirdGrid)
                count = -1
            }
        }
        imageRequestViewModel.addSelectedImages(gridSelection)
        gridView = binding.imageGridView
        gridView.adapter = imageAdapter
        gridView.setOnItemClickListener { _, view, position, _ ->
            val score = imageAdapter.getScore(position)
            val drawableName = imageAdapter.getDrawableName(gridSelectionAsList, position)!!
            intent = Intent(requireContext(), ImageResponse::class.java)
            intent.putExtra("drawableName", drawableName)
            intent.putExtra("score", score)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}