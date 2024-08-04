package inatools.backend.dto.bloodpressure;

import java.util.List;

public record BloodPressureListResponse(
        List<BloodPressureResponse> bloodPressureList
) {

}
