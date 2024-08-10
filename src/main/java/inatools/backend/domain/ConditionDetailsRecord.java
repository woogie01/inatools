package inatools.backend.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;

@Entity
@Getter
@Table(name = "condition_details_record")
public class ConditionDetailsRecord {

    @Id @GeneratedValue
    @Column(name = "condition_details_record_id")
    private Long id;
    
    private LocalDate recordDate;
    private String conditionDetails;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member member;
    
    protected ConditionDetailsRecord() {}
    
    public ConditionDetailsRecord(LocalDate recordDate, String conditionDetails, Member member) {
        this.recordDate = recordDate;
        this.conditionDetails = conditionDetails;
        this.member = member;
    }

    public static ConditionDetailsRecord createConditionDetailsRecord(String conditionDetails, Member member) {
        return new ConditionDetailsRecord(
                LocalDate.now(),
                conditionDetails,
                member
        );
    }

}
