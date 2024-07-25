package com.henriette.wholezen.activies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.henriette.wholezen.R
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Line
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipPositionMode
import com.henriette.wholezen.databinding.ActivityReflectBinding

class ReflectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReflectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReflectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupChart()

        binding.fabBack.setOnClickListener{
            val intent =Intent(this,WelcomeBackActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.tbarMoodSummary)
        supportActionBar?.title = "Mood Summary"
    }

    private fun setupChart() {
        val anyChartView = findViewById<AnyChartView>(R.id.summaryChart)

        val cartesian: Cartesian = AnyChart.line()

        cartesian.animation(true)

        cartesian.padding(10.0, 20.0, 5.0, 20.0)

        cartesian.crosshair().enabled(true)
        cartesian.crosshair()
            .yLabel(true)
            .yStroke(null as String?, null as Number?, null as String?, null as String?, null as String?)

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

        cartesian.title("Mood Summary Over Time")

        cartesian.yAxis(0).title("Mood Level")
        cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

        val seriesData = listOf(
            ValueDataEntry("Week1", 20),
            ValueDataEntry("Week2", 60),
            ValueDataEntry("Week3", 100),
            ValueDataEntry("Week4", 60)
        )

        val set = Set.instantiate()
        set.data(seriesData)
        val seriesMapping = set.mapAs("{ x: 'x', value: 'value' }")

        val series: Line = cartesian.line(seriesMapping)
        series.name("Mood Level")
        series.hovered().markers().enabled(true)
        series.hovered().markers()
            .type(MarkerType.STAR10)
            .size(4.0)
        series.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        anyChartView.setChart(cartesian)
    }
}