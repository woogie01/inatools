package inatools.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DrinkingStatus {

    NON_DRINKER("거의 안 만셔요"),
    MONTH_DRINKER("월 2회 이상 마셔요"),
    WEEK_DRINKER("주 2회 이상 마셔요"),
    DAILY_DRINKER("매일 마셔요");

    private final String description;

}
