package com.example.myapplication.fragments.company.analysis;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAnalysisBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class AnalysisFragment extends Fragment {

    private FragmentAnalysisBinding binding;
    private AnalysisViewModel mViewModel;

    public static AnalysisFragment newInstance() {
        return new AnalysisFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAnalysisBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(AnalysisViewModel.class);

        PieChart pieChart = binding.pieChart;
        loadPieChartData(pieChart);

        BarChart barChart = binding.barChart;
        loadBarChartData(barChart);

        return binding.getRoot();
    }

    private void loadBarChartData(BarChart barChart) {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, 10));
        entries.add(new BarEntry(2, 20));
        entries.add(new BarEntry(3, 30));
        entries.add(new BarEntry(4, 40));

        BarDataSet barDataSet = new BarDataSet(entries, "Bar Data Set");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        barChart.setFitBars(true);
        barChart.setData(new BarData(barDataSet));
        barChart.getDescription().setText("Bar Chart Description");
        barChart.animateY(2000);

    }

    private static void loadPieChartData(PieChart pieChart) {
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(0.2f, "A"));
        pieEntries.add(new PieEntry(0.10f, "B"));
        pieEntries.add(new PieEntry(0.3f, "C"));
        pieEntries.add(new PieEntry(0.4f, "D"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.MATERIAL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Categories");
        // set colors to be ColorTemplate.MATERIAL_COLORS
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);

        // Customize the chart's styling options
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setCenterTextSize(30);
        pieChart.setEntryLabelTextSize(20);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        // Update the chart
        pieChart.invalidate();
    }

}