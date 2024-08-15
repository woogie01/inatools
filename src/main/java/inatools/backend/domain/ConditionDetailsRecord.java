package inatools.backend.domain;

import inatools.backend.converter.CommonConditionConverter;
import inatools.backend.dto.condtiondetails.ConditionDetailsRecordRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
@Table(name = "condition_details_record")
public class ConditionDetailsRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_details_record_id")
    private Long id;
    
    private LocalDate recordAt;

    @Convert(converter = CommonConditionConverter.class)
    @Column(name = "common_conditions") // ['어지러움']
    private List<CommonCondition> commonConditionList;

    private String conditionDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
    protected ConditionDetailsRecord() {}
    
    public ConditionDetailsRecord(LocalDate recordAt, List<CommonCondition> commonConditionList, String conditionDetails, Member member) {
        this.recordAt = recordAt;
        this.commonConditionList = commonConditionList;
        this.conditionDetails = conditionDetails;
        this.member = member;
    }

    public static ConditionDetailsRecord createConditionDetailsRecord(ConditionDetailsRecordRequest request, Member member) {
        return new ConditionDetailsRecord(
                request.recordAt(),
                request.commonConditionList(),
                request.conditionDetails(),
                member
        );
    }

    public ConditionDetailsRecord updateConditionDetailsRecord(ConditionDetailsRecordRequest request) {
        this.conditionDetails = request.conditionDetails();
        this.commonConditionList = request.commonConditionList();
        return this;
    }

}
