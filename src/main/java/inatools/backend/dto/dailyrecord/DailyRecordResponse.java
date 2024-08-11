package inatools.backend.dto.dailyrecord;

import inatools.backend.dto.bloodpressure.BloodPressureListResponse;
import inatools.backend.dto.condtiondetails.ConditionDetailsListResponse;
import inatools.backend.dto.condtionrecord.ConditionRecordResponse;
import inatools.backend.dto.medication.MedicationInfoListResponse;
import inatools.backend.dto.medication.MedicationRecordListResponse;
import io.swagger.v3.oas.annotations.media.Schema;

public record DailyRecordResponse(

        @Schema(description = "혈압 측정 기록 목록")
        BloodPressureListResponse bloodPressureRecords,

        @Schema(description = "복용 약 기록 목록")
        MedicationRecordListResponse medicationRecords,

        @Schema(description = "컨디션 상태 기록")
        ConditionRecordResponse conditionRecord,

        @Schema(description = "몸 상세 기록 목록")
        ConditionDetailsListResponse conditionDetailsRecords
) {

}
