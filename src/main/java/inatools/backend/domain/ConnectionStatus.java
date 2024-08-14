package inatools.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConnectionStatus {
    REQUESTED("요청됨"),
    ACCEPTED("수락됨"),
    REJECTED("거절됨");

    private final String description;
}
