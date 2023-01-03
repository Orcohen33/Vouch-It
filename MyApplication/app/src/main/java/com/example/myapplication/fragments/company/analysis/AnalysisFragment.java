package com.example.myapplication.fragments.company.analysis;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentAnalysisBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class AnalysisFragment extends Fragment {

    private FragmentAnalysisBinding binding;
    private AnalysisViewModel mViewModel;
    private PieChart pieChart;

    private Long companyId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            companyId = this.getArguments().getLong("companyId");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAnalysisBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(AnalysisViewModel.class);

        mViewModel.init(companyId);
        getAnalysisData();

        pieChart = binding.pieChart;
        setupPieChart();
//        loadPieChartData(pieChart);

        BarChart barChart = binding.barChart;
//        loadBarChartData(barChart);


        return binding.getRoot();
    }

    private void getAnalysisData() {

        mViewModel.getAnalysisData().observe(getViewLifecycleOwner(), analysisData -> {
            System.out.println("Observing data");
            if (analysisData != null) {
                mViewModel.salesPerMonth = analysisData.get("salesPerMonth");
                mViewModel.totalCouponsSold = analysisData.get("totalCouponsSold");
                // pass data to pie chart
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    loadPieChartData(pieChart, mViewModel.salesPerMonth, mViewModel.totalCouponsSold.get("0"));
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Sales per month");
        pieChart.setCenterTextSize(20);
        pieChart.setTransparentCircleRadius(20f);
        pieChart.getDescription().setEnabled(false);
        // get the pie to bottom
        pieChart.setExtraOffsets(0, 0, 0, 0);
        pieChart.setVerticalFadingEdgeEnabled(true);
        // resize the size of the pie

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(4f);
        l.setYEntrySpace(1f);
        l.setYOffset(0f);
        l.setEnabled(true);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadPieChartData(PieChart pieChart, HashMap<String, Long> salesPerMonth, Long totalCouponsSold) {

        // function to convert month number to month name
        Function<String, String> convertNumberToNameOfMonth = s -> {
            switch (s) {
                case "1": {return "ינואר";}
                case "2": {return "פבואר";}
                case "3": {return "מרץ";}
                case "4": {return "אפריל";}
                case "5": {return "מאי";}
                case "6": {return "יוני";}
                case "7": {return "יולי";}
                case "8": {return "אוגוסט";}
                case "9": {return "ספטמבר";}
                case "10": {return "אוקטובר";}
                case "11": {return "נובמבר";}
                case "12": {return "דצמבר";}
                default: {return "לא ידוע";}
            }
        };
        // function to convert total sales per month to percentage
        Function<String, Float> convertSalesToPercentage = s -> (float) salesPerMonth.get(s) / totalCouponsSold * 100;

        // create a list of pie entries and initialize it
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(convertSalesToPercentage.apply("1"), convertNumberToNameOfMonth.apply("1")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("2"), convertNumberToNameOfMonth.apply("2")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("3"), convertNumberToNameOfMonth.apply("3")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("4"), convertNumberToNameOfMonth.apply("4")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("5"), convertNumberToNameOfMonth.apply("5")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("6"), convertNumberToNameOfMonth.apply("6")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("7"), convertNumberToNameOfMonth.apply("7")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("8"), convertNumberToNameOfMonth.apply("8")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("9"), convertNumberToNameOfMonth.apply("9")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("10"), convertNumberToNameOfMonth.apply("10")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("11"), convertNumberToNameOfMonth.apply("11")));
        entries.add(new PieEntry(convertSalesToPercentage.apply("12"), convertNumberToNameOfMonth.apply("12")));


        // create a pie data set (representing the data in the pie chart)
        PieDataSet dataSet = new PieDataSet(entries, "חודשים");
        dataSet.setSliceSpace(3f);  // space between slices
        ArrayList<Integer> colors = new ArrayList<>();
        // set colors to be ColorTemplate.MATERIAL_COLORS
        // set different color from material colors to each category
        for (int c : ColorTemplate.MATERIAL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }
        // on bottom of the page
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.BLACK);
        data.setDrawValues(true);
        pieChart.setData(data);

//        dataSet.setValueTextColor(Color.BLACK);
//        dataSet.setValueTextSize(16f);
//        dataSet.setFormSize(16f);
//        dataSet.setFormLineWidth(16f);
//        dataSet.setForm(Legend.LegendForm.CIRCLE);
////        dataSet.setXValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
////        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//
//
//        // inside pie chart
//        PieData data = new PieData(dataSet);
//        data.setDrawValues(true);
//        data.setValueFormatter(new PercentFormatter(pieChart));
//        data.setValueTextSize(18f);
//        data.setValueTextColor(Color.BLACK);
//        pieChart.setData(data);
//
//        // Customize the chart's styling options
//        pieChart.setUsePercentValues(true);
//        pieChart.getDescription().setEnabled(false);
//        pieChart.setExtraOffsets(0, 10, 5, 0);
//        pieChart.setDrawHoleEnabled(false);
//        pieChart.setCenterTextSize(30);
//        pieChart.setEntryLabelTextSize(20);
//        pieChart.setEntryLabelColor(Color.BLACK);
//        pieChart.setHoleColor(Color.BLACK);
//        pieChart.setTransparentCircleRadius(61f);

        // Update the chart
        pieChart.invalidate();
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

}