package inatools.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SmokingStatus {

    NON_SMOKER("안피워요"),
    FORMER_SMOKER("과거에만 피웠어요"),
    OCCASIONAL_SMOKER("가끔 피워요"),
    DAILY_SMOKER("매일 피워요");

    private final String description;

}
