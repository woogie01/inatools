package inatools.backend.dto.strokecheck;

import inatools.backend.domain.StrokeCheck;
import inatools.backend.domain.StrokeCheckTestType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

public record StrokeCheckResponse(

        @Schema(description = "뇌졸중 검사 식별자", example = "1")
        Long strokeCheckId,

        @Schema(description = "검사 날짜", example = "2024-08-15")
        LocalDate recordDate,

        @Schema(description = "검사 횟수", example = "3")
        Long testCount,

        @Schema(description = "검사 결과 평균 퍼센트", example = "60")
        Double testResultAvg,

        @Schema(description = "테스트 종류", example = "얼굴 비대칭 테스트")
        StrokeCheckTestType testType,

        @Schema(description = "회원 식별자", example = "1")
        Long memberId
) {


        public static StrokeCheckResponse fromStrokeCheck(StrokeCheck strokeCheck) {
                return new StrokeCheckResponse(
                        strokeCheck.getId(),
                        strokeCheck.getRecordDate(),
                        strokeCheck.getTestCount(),
                        strokeCheck.getTestResultAvg(),
                        strokeCheck.getTestType(),
                        strokeCheck.getMember().getId()
                );
        }
}
