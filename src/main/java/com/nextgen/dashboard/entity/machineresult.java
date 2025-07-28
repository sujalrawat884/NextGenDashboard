package com.nextgen.dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "dg_lab_test_machine_result")
public class machineresult {
//@Transient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idkey;
    private String batch_no;
    private Timestamp create_date;
}
