package com.nextgen.dashboard.service;

import com.nextgen.dashboard.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    public List<Integer> getDistinctYears() {
        return dashboardRepository.getYear();
    }

    public List<Map<Integer, Integer>> getMonthlyBatchCounts(int year) {
        List<Map<String, Object>> monthlyCounts = dashboardRepository.findDistinctBatchNoByMonthGroupByCreateDate(year);
        List<Map<Integer, Integer>> chartData = monthlyCounts.stream()
                .map(row -> {
                    int month = Integer.parseInt(row.get("MONTH").toString());
                    int count = Integer.parseInt(row.get("COUNT").toString());
                    return Map.of(month, count);
                })
                .toList();
        System.out.println("chartData: " + chartData);
        // Combine all input maps into one full map
        Map<Integer, Integer> resultMap = new HashMap<>();
        chartData.forEach(resultMap::putAll);

        // Ensure keys 1-12 are present
        for (int month = 1; month <= 12; month++) {
            resultMap.putIfAbsent(month, 0);
        }
        // Optional: sort by month
        Map<Integer, Integer> sortedMap = new TreeMap<>(resultMap);


        return List.of(sortedMap);
    }

    public List<Map<String, Integer>> getMachineBatchCountByYear(int year) {
        List<Map<String, Object>> machineBatchCounts = dashboardRepository.getMachineBatchCountByYear(year);
        List<Map<String, Integer>> chartData = machineBatchCounts.stream()
                .map(row -> {
                    String MachineId = row.get("MachineId").toString();
                    int BatchCount = Integer.parseInt(row.get("BatchCount").toString());
                    return Map.of(MachineId, BatchCount);
                })
                .toList();
        return chartData;
    }

    public List<Map<Integer, Integer>> getMachineDetailsById(String machineId, int year) {
        List<Map<String, Object>> machineDetails = dashboardRepository.getMachineDetailById(machineId, year);
        List<Map<Integer, Integer>> chartData = machineDetails.stream()
                .map(row -> {
                    int month = Integer.parseInt(row.get("MONTH").toString());
                    int count = Integer.parseInt(row.get("COUNT").toString());
                    return Map.of(month, count);
                })
                .toList();
        System.out.println("chartData: " + chartData);
        // Combine all input maps into one full map
        Map<Integer, Integer> resultMap = new HashMap<>();
        chartData.forEach(resultMap::putAll);
        // Ensure keys 1-12 are present
        for (int month = 1; month <= 12; month++) {
            resultMap.putIfAbsent(month, 0);
        }
        // Optional: sort by month
        Map<Integer, Integer> sortedMap = new TreeMap<>(resultMap);
        return List.of(sortedMap);
    }




}
