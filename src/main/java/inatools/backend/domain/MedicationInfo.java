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
    private Long dosage; // 복용 횟수
    // ToDO: 복용 종료일이나 현재 복용 중인지를 저장하는 필드 추가

    @ManyToOne(fetch = FetchType.LAZY)
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

    public void updateMedicationInfo(MedicationInfoRequest medicationInfoRequest) {
        this.medicationName = medicationInfoRequest.medicationName();
        this.dosage = medicationInfoRequest.dosage();
    }

}
