package inatools.backend.dto.strokecheck;

import inatools.backend.domain.StrokeCheckTestType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record StrokeCheckRequest(

        @NotNull
        @Schema(description = "검사 날짜", example = "2024-08-15")
        LocalDate recordAt,

        @NotNull
        @Schema(description = "표정 검사 ", example = "60")
        Double testResultPercent,

        @NotNull
        @Schema(description = "테스트 종류", example = "FACE_TEST")
        StrokeCheckTestType testType,

        @NotNull
        @Schema(description = "회원 식별자", example = "1")
        Long memberId
) {

}
