package inatools.backend.domain;

import inatools.backend.dto.condtiondetails.ConditionDetailsRecordRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "condition_details_record")
public class ConditionDetailsRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_details_record_id")
    private Long id;
    
    private LocalDate recordDate;

    @Enumerated(value = EnumType.STRING)
    private CommonConditionDetails commonConditionDetails;

    private String conditionDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    
    protected ConditionDetailsRecord() {}
    
    public ConditionDetailsRecord(LocalDate recordDate, CommonConditionDetails commonConditionDetails, String conditionDetails, Member member) {
        this.recordDate = recordDate;
        this.commonConditionDetails = commonConditionDetails;
        this.conditionDetails = conditionDetails;
        this.member = member;
    }

    public static ConditionDetailsRecord createConditionDetailsRecord(ConditionDetailsRecordRequest request, Member member) {
        return new ConditionDetailsRecord(
                LocalDate.now(),
                request.commonConditionDetails(),
                request.conditionDetails(),
                member
        );
    }

    public ConditionDetailsRecord updateConditionDetailsRecord(ConditionDetailsRecordRequest request) {
        this.conditionDetails = request.conditionDetails();
        this.commonConditionDetails = request.commonConditionDetails();
        return this;
    }

}
