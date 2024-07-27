package inatools.backend.domain;

import inatools.backend.dto.medication.MedicationInfoRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "medication_info")
public class MedicationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medication_info_id")
    private Long id;

    private String medicationName; // 약 이름
    private Long dosage; // 복용량

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    protected MedicationInfo() {
    }

    public MedicationInfo(String medicationName, Long dosage, Member member) {
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.member = member;
    }

    public static MedicationInfo createMedicationInfo(MedicationInfoRequest medicationInfoRequest, Member member) {
        return new MedicationInfo(
                medicationInfoRequest.medicationName(),
                medicationInfoRequest.dosage(),
                member
        );
    }
}
