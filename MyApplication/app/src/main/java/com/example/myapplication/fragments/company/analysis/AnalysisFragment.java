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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

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
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(10, "A"));
        pieEntries.add(new PieEntry(20, "B"));
        pieEntries.add(new PieEntry(30, "C"));

        PieDataSet dataSet = new PieDataSet(pieEntries, "Categories");
        // set colors to be ColorTemplate.MATERIAL_COLORS
        dataSet.setColors(R.color.purple_200, R.color.purple_500, R.color.purple_700);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);

        // Customize the chart's styling options
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        // Update the chart
        pieChart.invalidate();

        return binding.getRoot();
    }

}