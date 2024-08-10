package inatools.backend.domain;

import lombok.Getter;

@Getter
public enum ConditionType {
    BEST("최고"),
    GOOD("좋음"),
    NORMAL("보통"),
    BAD("나쁨"),
    WORST("아픔");

    private final String description;

    ConditionType(String description) {
        this.description = description;
    }
}
