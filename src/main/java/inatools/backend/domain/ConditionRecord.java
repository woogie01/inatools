package inatools.backend.domain;

import inatools.backend.dto.condtionrecord.ConditionRecordRequest;
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
@Table(name = "condition_record")
public class ConditionRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_record_id")
    private Long id;

    private LocalDate recordAt;

    @Enumerated(value = EnumType.STRING)
    private ConditionType conditionType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    protected ConditionRecord() {}

    public ConditionRecord(LocalDate recordAt, ConditionType conditionType, Member member) {
        this.recordAt = recordAt;
        this.conditionType = conditionType;
        this.member = member;
    }

    public static ConditionRecord createConditionRecord(ConditionRecordRequest request, Member member) {
        return new ConditionRecord(
                request.recordAt(),
                request.conditionType(),
                member
        );
    }

    public ConditionRecord updateConditionRecord(ConditionRecordRequest request) {
        this.conditionType = request.conditionType();
        return this;
    }
}
