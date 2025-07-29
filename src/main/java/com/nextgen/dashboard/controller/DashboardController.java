package com.nextgen.dashboard.controller;

import com.nextgen.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public String getDistinctYears(Model model) {
        List<Integer> years = dashboardService.getDistinctYears();
        System.out.println("years: " + years);
        model.addAttribute("years", years);
        return "dashboard";
    }



    @GetMapping("/batchdetails")
    public String getBatchMonthly(Model model, @RequestParam("year") int year) {
        List<Map<Integer, Integer>> monthlyCounts = dashboardService.getMonthlyBatchCounts(year);
        System.out.println("monthlyCounts: " + monthlyCounts);
        List<Integer> month = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        for (Map<Integer, Integer> map : monthlyCounts) {
            month.addAll(map.keySet());
            count.addAll(map.values());
        }
        List<Integer> years = dashboardService.getDistinctYears();
        List<Map<String, Integer>> machineBatchCounts = dashboardService.getMachineBatchCountByYear(year);
        System.out.println("machineBatchCounts: " + machineBatchCounts);
        List<String> machineIds = new ArrayList<>();
        List<Integer> batchCounts = new ArrayList<>();
        for (Map<String, Integer> map : machineBatchCounts) {
            machineIds.addAll(map.keySet());
            batchCounts.addAll(map.values());
        }
        model.addAttribute("MachineId", machineIds);
        model.addAttribute("BatchCount", batchCounts);
        model.addAttribute("selectedYear", year);
        model.addAttribute("years", years);
        model.addAttribute("month", month);
        model.addAttribute("count", count);
        return "dashboard";
    }


    @GetMapping("/machinedetails/{machineId}")
    public String getMachineDetails(Model model, @PathVariable String machineId, @RequestParam(value = "year", required = false) Integer year) {

        // If year is not provided, use current year
        if (year == null) {
            year = java.time.Year.now().getValue();
        }

        List<Map<Integer, Integer>> machineDetails = dashboardService.getMachineDetailsById(machineId, year);
        System.out.println("machineDetails: " + machineDetails);
        List<Integer> month = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        for (Map<Integer, Integer> map : machineDetails) {
            month.addAll(map.keySet());
            count.addAll(map.values());
        }
        List<Integer> years = dashboardService.getDistinctYears();
        model.addAttribute("selectedYear", year);
        model.addAttribute("years", years);
        model.addAttribute("machineId", machineId);
        model.addAttribute("month", month);
        model.addAttribute("count", count);
        return "machinedetails";
    }



}
