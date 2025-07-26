package com.nextgen.dashboard;

import com.nextgen.dashboard.repository.DashboardRepository;
import com.nextgen.dashboard.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.toList;

@SpringBootTest
public class DashboardTest {
    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private DashboardService dashboardService;

    @Test
    public void testDashboard() {
        List<Map<Integer, Integer>> monthlyCounts = dashboardService.getMonthlyBatchCounts();
        // Combine all input maps into one full map
        Map<Integer, Integer> resultMap = new HashMap<>();
        monthlyCounts.forEach(resultMap::putAll);
        // Ensure keys 1-12 are present
        for (int month = 1; month <= 12; month++) {
            resultMap.putIfAbsent(month, 0);
        }
        // Optional: sort by month
        Map<Integer, Integer> sortedMap = new TreeMap<>(resultMap);
        System.out.println(sortedMap);

    }




}
