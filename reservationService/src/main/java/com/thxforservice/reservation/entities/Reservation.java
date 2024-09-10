package com.thxforservice.reservation.entities;

import com.thxforservice.global.entities.BaseEntity;
import com.thxforservice.reservation.constants.CCase;
import com.thxforservice.reservation.constants.CReason;
import com.thxforservice.reservation.constants.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue
    private Long cSeq;

    @Column(length=45, nullable = false)
    private String gid;

    @Column(nullable = false)
    private LocalDate rDate;

    @Column(nullable = false)
    private LocalTime rTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CReason cReason; //상담경위

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CCase cCase;  //상담유형

    @Column(length = 65)
    private String cCaseDetail; //기타상담유형

    @Column(nullable = false)
    private String memberID;

    @Column(nullable = false, length = 65)
    private String studentNo;

    @Column(nullable = false, length = 65)
    private String empNo;

    @Column(nullable = false, length = 65)
    private String mobile;

    @Column(length = 65)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; //예약상태

    @Column(nullable = false, length = 65)
    private String agree;

    @Lob
    private String content; // 상담 내용
}

