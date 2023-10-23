package com.example.shadman_hossain_stressmeter.ui.visualization

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.shadman_hossain_stressmeter.CSVAdapter
import com.example.shadman_hossain_stressmeter.ImageResponse
import com.example.shadman_hossain_stressmeter.R
import com.example.shadman_hossain_stressmeter.databinding.FragmentVisualizationBinding
import com.google.android.material.color.utilities.Score
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lecho.lib.hellocharts.model.Axis
import lecho.lib.hellocharts.model.ChartData
import lecho.lib.hellocharts.model.Line
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.PointValue
import lecho.lib.hellocharts.view.Chart
import lecho.lib.hellocharts.view.LineChartView

class VisualizationFragment : Fragment() {

    private var _binding: FragmentVisualizationBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var chart:LineChartView
    private lateinit var tableLayout: TableLayout
    private lateinit var csvData: List<String>
    private var values = ArrayList<PointValue>()
    private lateinit var line:Line
    private var lines = ArrayList<Line>()
    private lateinit var data: LineChartData
    private lateinit var axisX: Axis
    private lateinit var axisY:Axis
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val visualizationViewModel =
            ViewModelProvider(this).get(VisualizationViewModel::class.java)

        _binding = FragmentVisualizationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        chart = binding.root.findViewById<LineChartView>(R.id.resultsGraph)
//        chart = LineChartView(requireContext())
        val csvAdapter = CSVAdapter(requireContext())
        tableLayout = binding.root.findViewById(R.id.resultsTable)
        CoroutineScope(Dispatchers.IO).launch {
            csvData = csvAdapter.readDataFromCSVFile()
            for (data in csvData){
                var parts = data.split(",")
                if(parts.size == 2){
                    var score = parts[0].toFloat()
                    var time = parts[1].toFloat()
                    values.add(PointValue(time, score))
                }
            }
            visualizationViewModel.updateVisualizationData(values)
            updateGraph(values)
        }

        line = Line(values).setColor(Color.BLUE).setCubic(true)
        lines.add(line)
        data = LineChartData()
        axisX = Axis()
        axisX.name = "Time"
        axisY = Axis()
        axisY.name =  "Stress Level"
        data.axisXBottom = axisX
        data.axisYLeft = axisY
        data.setLines(lines)
        chart.setLineChartData(data)
        return root
    }

    private fun updateGraph(updateData: List<PointValue>){
        values.clear()
        values.addAll(updateData)
        val updatedLine = Line(values).setColor(Color.BLUE).setCubic(true)
        lines.clear()
        lines.add(updatedLine)
        val updatedData = LineChartData()
        updatedData.axisXBottom = axisX
        updatedData.axisYLeft = axisY
        updatedData.setLines(lines)
        chart.setLineChartData(updatedData)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}