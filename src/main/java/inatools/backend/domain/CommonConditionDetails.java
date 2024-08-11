package inatools.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonConditionDetails {
    DIZZINESS("어지러움"),
    WEAKNESS("힘이 없음"),
    VISION_PROBLEM("시력이 나빠짐"),
    SPEECH_DIFFICULTY("말하기 어려움"),
    SLURRED_SPEECH("발음이 나빠짐");

    private final String description;
}
