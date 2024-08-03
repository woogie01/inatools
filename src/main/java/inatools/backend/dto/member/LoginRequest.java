package inatools.backend.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @Size(min = 5, max = 50)
        @NotBlank(message = "아이디를 입력해주세요.")
        @Schema(description = "아이디", example = "pil0602")
        String userId,

        @Size(min = 6, max = 12, message = "비밀번호는 6자 이상, 12자 이하여야 합니다.")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Schema(description = "비밀번호", example = "010602")
        String password
) {
}