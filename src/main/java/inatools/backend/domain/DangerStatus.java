package inatools.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DangerStatus {
    SAFE("안전"),
    DANGER("위험");

    private final String description;
}
