package com.nextgen.dashboard;

import com.nextgen.dashboard.repository.DashboardRepository;
import com.nextgen.dashboard.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Map;


@SpringBootTest
public class DashboardTest {
    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private DashboardService dashboardService;

    @Test
    public void testDashboard() {


    }




}
