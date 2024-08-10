package inatools.backend.domain;

import lombok.Getter;

@Getter
public enum StrokeCheckTestType {

    FACE_TEST("얼굴 비대칭 테스트"),
    BODY_TEST("팔 비대칭 테스트"),
    PRONUNCIATION_TEST("발음 테스트");

    private final String description;

    StrokeCheckTestType(String description) { this.description = description; }

}
