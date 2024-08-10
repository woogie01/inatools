package inatools.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StrokeCheckTestType {

    FACE_TEST("얼굴 비대칭 테스트"),
    BODY_TEST("팔 비대칭 테스트"),
    PRONUNCIATION_TEST("발음 테스트");

    private final String description;

}
