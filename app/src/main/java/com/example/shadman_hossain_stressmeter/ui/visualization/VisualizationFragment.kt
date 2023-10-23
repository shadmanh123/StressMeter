package com.example.shadman_hossain_stressmeter.ui.visualization

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shadman_hossain_stressmeter.CSVAdapter
import com.example.shadman_hossain_stressmeter.ImageResponse
import com.example.shadman_hossain_stressmeter.R
import com.example.shadman_hossain_stressmeter.RecyclerViewCustomAdapter
import com.example.shadman_hossain_stressmeter.StressData
import com.example.shadman_hossain_stressmeter.databinding.FragmentVisualizationBinding
import com.google.android.material.color.utilities.Score
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lecho.lib.hellocharts.model.Axis
import lecho.lib.hellocharts.model.AxisValue
import lecho.lib.hellocharts.model.ChartData
import lecho.lib.hellocharts.model.Line
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.PointValue
import lecho.lib.hellocharts.view.Chart
import lecho.lib.hellocharts.view.LineChartView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class VisualizationFragment : Fragment() {

    private var _binding: FragmentVisualizationBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var chart:LineChartView
    private lateinit var recyclerView: RecyclerView
    private lateinit var csvData: List<String>
    private lateinit var line:Line
    private var lines = ArrayList<Line>()
    private lateinit var data: LineChartData
    private lateinit var axisX: Axis
    private lateinit var axisY:Axis
    private lateinit var values: ArrayList<PointValue>
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    private lateinit var axisValue: List<Int>

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
        chart.setInteractive(false)
        val csvAdapter = CSVAdapter(requireContext())
        recyclerView = binding.root.findViewById(R.id.resultsTable)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val backgroundThread = Thread {
            csvData = csvAdapter.readDataFromCSVFile()
            values = ArrayList<PointValue>()
            var stressData = mutableListOf<StressData>()
            var instance : Int = 0
            for (data in csvData){
                var parts = data.split(",")
                if(parts.size == 2){
                    var score = parts[0].toFloat()
                    var timeInFloat = parts[1].toFloat()
                    var timeInLong = timeInFloat.toLong()
                    var timeStamp = simpleDateFormat.format(Date(timeInLong))
                    values.add(PointValue(instance.toFloat(), score))
                    stressData.add(StressData(score,timeStamp))
                    instance++
                }
            }
            activity?.runOnUiThread{
                visualizationViewModel.updateVisualizationData(values)
                axisX = Axis()
                axisX.name = "Instance"
                axisX.textColor = R.color.Isabelline
                axisY = Axis()
                axisY.name =  "Stress Level"
                axisY.textColor = R.color.Isabelline
                var count = 0
                axisY.setValues(
                    listOf( AxisValue(1f), AxisValue(2f), AxisValue(3f),
                        AxisValue(4f), AxisValue(5f), AxisValue(6f),
                        AxisValue(7f), AxisValue(8f), AxisValue(9f),
                        AxisValue(10f), AxisValue(11f), AxisValue(12f),
                        AxisValue(13f), AxisValue(14f), AxisValue(15f),
                        AxisValue(16f)
                    )
                )
                val adapter = RecyclerViewCustomAdapter(stressData)
                recyclerView.adapter = adapter
                updateGraph(values)
            }

        }
        backgroundThread.start()

        return root
    }

    private fun updateGraph(updateData: ArrayList<PointValue>){
        val updatedLine = Line(updateData).setColor(Color.BLUE).setCubic(true)
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