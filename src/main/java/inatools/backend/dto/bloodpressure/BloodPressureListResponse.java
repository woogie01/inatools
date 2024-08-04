package inatools.backend.dto.bloodpressure;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record BloodPressureListResponse(

        @Schema(description = "혈압 측정 기록 목록")
        List<BloodPressureResponse> bloodPressureList
) {

}
