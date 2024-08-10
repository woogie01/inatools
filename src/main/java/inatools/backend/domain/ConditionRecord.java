package inatools.backend.domain;

import inatools.backend.dto.condtionrecord.ConditionRecordRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;

@Entity
@Getter
@Table(name = "condition_record")
public class ConditionRecord {

    @Id @GeneratedValue
    @Column(name = "condition_record_id")
    private Long id;

    private LocalDate recordDate;

    @Enumerated(value = EnumType.STRING)
    private ConditionType conditionType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member member;

    protected ConditionRecord() {}

    public ConditionRecord(LocalDate recordDate, ConditionType conditionType, Member member) {
        this.recordDate = recordDate;
        this.conditionType = conditionType;
        this.member = member;
    }

    public static ConditionRecord createConditionRecord(ConditionRecordRequest request, Member member) {
        return new ConditionRecord(
                LocalDate.now(),
                request.conditionType(),
                member
        );
    }

}
