package com.nextgen.dashboard.controller;

import com.nextgen.dashboard.repository.DashboardRepository;
import com.nextgen.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {
    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private DashboardService dashboardService;

//    @GetMapping
//    public String testDashboard(Model model) {
//        List<Integer> count = List.of(dashboardRepository.findDistinctBatchNoByMonth(8));
//        model.addAttribute("count", count);
//        return "dashboard";
//    }
    @GetMapping
    public String getDistinctYears(Model model) {
        List<Integer> years = dashboardService.getDistinctYears();
        System.out.println("years: " + years);
        model.addAttribute("years", years);
        return "dashboard";
    }

    @GetMapping("/month")
    public String getBatchMonthly(Model model, @RequestParam("year") int year) {
        List<Map<Integer, Integer>> monthlyCounts = dashboardService.getMonthlyBatchCounts(year);
        System.out.println("monthlyCounts: " + monthlyCounts);
        List<Integer> month = new ArrayList<>();
        for (Map<Integer, Integer> map : monthlyCounts) {
            month.addAll(map.keySet());
        }
        List<Integer> count = new ArrayList<>();
        for (Map<Integer, Integer> map : monthlyCounts) {
            count.addAll(map.values());
        }
        List<Integer> years = dashboardService.getDistinctYears();
        model.addAttribute("selectedYear", year);
        model.addAttribute("years", years);
        model.addAttribute("month", month);
        model.addAttribute("count", count);
        return "dashboard";
    }


//    @GetMapping("/values")
//    public void showData() {
//
//    }
}
