package com.nextgen.dashboard.service;

import com.nextgen.dashboard.entity.machineresult;
import com.nextgen.dashboard.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    public List<Map<Integer, Integer>> getMonthlyBatchCounts() {
        List<Map<String, Object>> monthlyCounts = dashboardRepository.findDistinctBatchNoByMonthGroupByCreateDate();
        List<Map<Integer, Integer>> chartData = monthlyCounts.stream()
                .map(row -> {
                    int month = Integer.parseInt(row.get("MONTH").toString());
                    int count = Integer.parseInt(row.get("COUNT").toString());
                    return Map.of(month, count);
                })
                .collect(Collectors.toList());
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

        return List.of(sortedMap); // Return as a list of maps for consistency with the original method signature



    }




}
