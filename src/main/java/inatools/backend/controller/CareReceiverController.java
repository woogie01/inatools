package inatools.backend.controller;


import inatools.backend.dto.carereceiver.UserCareConnectionRequest;
import inatools.backend.dto.carereceiver.UserCareConnectionResponse;
import inatools.backend.service.CareReceiverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 연결 관련 로직", description = "유저 연결 API")
@RestController
@RequestMapping("/api/care-receivers")
@RequiredArgsConstructor
public class CareReceiverController {

    private final CareReceiverService careReceiverService;

    /**
     * 연결 요청 API
     */
    @Operation(summary = "유저 간의 연결 추가", description = "기록 관리 권한을 부여하기 위한 API입니다.")
    @PostMapping
    public ResponseEntity<UserCareConnectionResponse> requestConnection(
            @RequestBody @Valid UserCareConnectionRequest userCareConnectionRequest, Principal principal) {
        String loginId = principal.getName();
        UserCareConnectionResponse response =
                careReceiverService.createRequest(loginId, userCareConnectionRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * 관리 대상자 삭제 API
     */

    /**
     * 관리 대상자 정보 조회 API
     */


}
