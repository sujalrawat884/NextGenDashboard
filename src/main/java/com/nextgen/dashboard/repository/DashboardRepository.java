package com.nextgen.dashboard.repository;

import com.nextgen.dashboard.entity.machineresult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DashboardRepository extends JpaRepository<machineresult, String> {

    @Query(value = "SELECT MONTH(create_date) AS MONTH,COUNT(DISTINCT batch_no) AS COUNT FROM `dg_lab_test_machine_result` WHERE YEAR(create_date)= :year GROUP BY MONTH(create_date)", nativeQuery = true)
    List<Map<String, Object>> findDistinctBatchNoByMonthGroupByCreateDate(@Param("year") int year);

    @Query(value = "SELECT DISTINCT YEAR(create_date) FROM `dg_lab_test_machine_result`", nativeQuery = true)
    List<Integer> getYear();

}
