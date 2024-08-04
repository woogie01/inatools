package inatools.backend.domain;

import inatools.backend.dto.bloodpressure.BloodPressureRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Getter
@Table(name = "blood_pressure")
public class BloodPressure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blood_pressure_id")
    private Long id;

    private LocalDateTime measureDateTime; // 기록 날짜
    private Long measurementNumber; // 측정 회차
    private Long systolicPressure; // 수축기 혈압 값
    private Long diastolicPressure; // 이완기 혈압 값

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member member;

    protected  BloodPressure() {
    }

    public BloodPressure(LocalDateTime measureDateTime, Long measurementNumber, Long systolicPressure, Long diastolicPressure, Member member) {
        this.measureDateTime = measureDateTime;
        this.measurementNumber = measurementNumber;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.member = member;
    }

    public static BloodPressure createBloodPressure(BloodPressureRequest bloodPressureRequest, Member member) {
        return new BloodPressure(
                LocalDateTime.now(),
                bloodPressureRequest.measurementNumber(),
                bloodPressureRequest.systolicPressure(),
                bloodPressureRequest.diastolicPressure(),
                member
        );
    }
}
