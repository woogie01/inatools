package inatools.backend.domain;

import inatools.backend.dto.medication.MedicationRecordRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;

@Entity
@Getter
@Table(name = "medication_record")
public class MedicationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medication_record_id")
    private Long id;

    private boolean isTaken;

    private LocalDate recordAt; // 기록 날짜

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MedicationInfo medicationInfo;

    protected MedicationRecord() {}

    public MedicationRecord(LocalDate recordAt, MedicationInfo medicationInfo) {
        this.isTaken = false;
        this.recordAt = recordAt;
        this.medicationInfo = medicationInfo;
    }

    public static MedicationRecord createMedicationRecord(MedicationRecordRequest request,
            MedicationInfo medicationInfo) {
        return new MedicationRecord(
                request.recordAt(),
                medicationInfo
        );
    }

    public MedicationRecord updateMedicationRecord(boolean isTaken) {
        this.isTaken = isTaken;
        return this;
    }
}
