CREATE TABLE member
(
    member_id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    username           VARCHAR(10) NOT NULL,
    user_id            VARCHAR(20) NOT NULL,
    password           VARCHAR(256) NOT NULL,
    email              VARCHAR(50) NOT NULL,
    phone              VARCHAR(15) NOT NULL,
    gender             BIGINT,
    birth_date         DATE,
    underlying_disease VARCHAR(255),
    family_history     BOOLEAN,
    smoking_status     VARCHAR(20),
    drinking_status    VARCHAR(20),
    danger_status      VARCHAR(20),
    role               VARCHAR(10) DEFAULT 'USER',
    created_at         TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE token
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY, -- 기본 키로 자동 증가하는 BIGINT
    mem_id        BIGINT       NOT NULL,             -- 회원 ID, 외래 키가 아닌 단순히 회원 ID로 사용됨
    refresh_token VARCHAR(255) NOT NULL,             -- Refresh Token 값, 고유해야 함
    UNIQUE (mem_id),                                 -- 회원 ID는 유일해야 함
    UNIQUE (refresh_token)                           -- Refresh Token 값도 유일해야 함
);

CREATE TABLE user_care_connections
(
    user_care_connection_id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 기본 키로 자동 증가하는 BIGINT
    connection_status       VARCHAR(20) NOT NULL,              -- 요청 상태 (ENUM 값을 VARCHAR로 저장)
    requested_member_id     BIGINT,                            -- 연결 요청을 받은 회원의 식별자
    requesting_member_id    BIGINT,                            -- 연결 요청을 보낸 회원의 식별자

    CONSTRAINT fk_user_care_requested_member
        FOREIGN KEY (requested_member_id)
            REFERENCES member (member_id)
            ON DELETE CASCADE,

    CONSTRAINT fk_user_care_requesting_member
        FOREIGN KEY (requesting_member_id)
            REFERENCES member (member_id)
            ON DELETE CASCADE
);

CREATE TABLE blood_pressure
(
    blood_pressure_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    record_at          DATE   NOT NULL, -- 기록 날짜
    record_number      BIGINT NOT NULL, -- 측정 회차
    systolic_pressure  BIGINT NOT NULL, -- 수축기 혈압 값
    diastolic_pressure BIGINT NOT NULL, -- 이완기 혈압 값
    member_id          BIGINT,          -- 외래키로 회원 식별자

    CONSTRAINT fk_blood_pressure_member
        FOREIGN KEY (member_id)
            REFERENCES member (member_id)
            ON DELETE CASCADE
);

CREATE TABLE stroke_check
(
    stroke_check_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    record_at       DATE        NOT NULL,
    test_count      BIGINT      NOT NULL,
    test_result_avg DOUBLE      NOT NULL,
    test_type       VARCHAR(20) NOT NULL,
    member_id       BIGINT,
    CONSTRAINT fk_stroke_check_member
        FOREIGN KEY (member_id)
            REFERENCES member (member_id)
            ON DELETE CASCADE
);

CREATE TABLE medication_info
(
    medication_info_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    medication_name    VARCHAR(50) NOT NULL,
    dosage             BIGINT      NOT NULL,
    active             BOOLEAN DEFAULT TRUE,
    member_id          BIGINT,
    CONSTRAINT fk_medication_info_member
        FOREIGN KEY (member_id)
            REFERENCES member (member_id)
            ON DELETE CASCADE
);

CREATE TABLE medication_record
(
    medication_record_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    is_taken             BOOLEAN NOT NULL DEFAULT FALSE,
    record_at            DATE    NOT NULL,
    medication_info_id   BIGINT,
    CONSTRAINT fk_medication_record_medication_info
        FOREIGN KEY (medication_info_id)
            REFERENCES medication_info (medication_info_id)
            ON DELETE CASCADE
);

CREATE TABLE condition_record
(
    condition_record_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    record_at           DATE        NOT NULL,
    condition_type      VARCHAR(20) NOT NULL, -- ConditionType 열거형을 문자열로 저장
    member_id           BIGINT,
    CONSTRAINT fk_condition_record_member
        FOREIGN KEY (member_id)
            REFERENCES member (member_id)
            ON DELETE CASCADE
);

CREATE TABLE condition_details_record
(
    condition_details_record_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    record_at                   DATE         NOT NULL,
    common_conditions           VARCHAR(20)  NOT NULL, -- CommonCondition 리스트를 변환하여 저장
    condition_details           VARCHAR(255) NOT NULL,
    member_id                   BIGINT,
    CONSTRAINT fk_condition_details_record_member FOREIGN KEY (member_id) REFERENCES member (member_id) ON DELETE CASCADE
);