package inatools.backend.controller;


import inatools.backend.dto.userconnection.UserCareConnectionListResponse;
import inatools.backend.dto.userconnection.UserCareConnectionRequest;
import inatools.backend.dto.userconnection.UserCareConnectionResponse;
import inatools.backend.dto.userconnection.UserConnectionReplyRequest;
import inatools.backend.service.UserConnectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 연결 관련 로직", description = "유저 연결 API")
@RestController
@RequestMapping("/api/user-connection")
@RequiredArgsConstructor
public class UserConnectionController {

    private final UserConnectionService userConnectionService;

    /**
     * 연결 요청 API
     */
    @Operation(summary = "유저 간의 연결 추가", description = "기록 관리 권한을 부여하기 위한 API입니다.")
    @PostMapping
    public ResponseEntity<UserCareConnectionResponse> requestConnection(
            @RequestBody @Valid UserCareConnectionRequest userCareConnectionRequest, Principal principal) {
        String loginId = principal.getName();
        UserCareConnectionResponse response =
                userConnectionService.createRequest(loginId, userCareConnectionRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * 연결 유저 전체 조회 API
     */
    @Operation(summary = "유저 간의 연결 정보 전체 조회", description = "연결 상태(수락, 요청)에 있는 유저 전체를 조회하기 위한 API입니다.")
    @GetMapping("/members/{id}")
    public ResponseEntity<UserCareConnectionListResponse> getConnectedUsers(
            @PathVariable("id") Long memberId, Principal principal) {
        String loginId = principal.getName();
        UserCareConnectionListResponse response = userConnectionService.getConnections(loginId, memberId);
        return ResponseEntity.ok(response);
    }

    /**
     * 연결 요청 응답 API
     */
    @Operation(summary = "유저 간의 연결 응답(수락, 거절)", description = "유저 간의 연결을 수락하거나 거절하기 위한 API입니다.")
    @PostMapping("/{id}/reply")
    public ResponseEntity<UserCareConnectionResponse> responseConnection(
            @PathVariable("id") Long userCareConnectionId,
            @RequestBody @Valid UserConnectionReplyRequest userConnectionReplyRequest, Principal principal) {
        String loginId = principal.getName();
        UserCareConnectionResponse response =
                userConnectionService.replyToRequest(loginId, userCareConnectionId, userConnectionReplyRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * 연결 요청 취소 API
     */


}
