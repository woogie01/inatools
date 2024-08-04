package inatools.backend.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateMemberRequest(

        @NotNull
        @Schema(description = "회원 식별자", example = "1")
        Long id,

        @NotEmpty(message = "이름을 입력해주세요.")
        @Schema(description = "회원명", example = "pilhosss")
        String name,

        @Size(min = 5, max = 50)
        @NotBlank(message = "아이디를 입력해주세요.")
        @Schema(description = "아이디", example = "pil1016")
        String userId,

        @Size(min = 6, max = 12, message = "비밀번호는 6자 이상, 12자 이하여야 합니다.")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Schema(description = "비밀번호", example = "011016")
        String password,

        @Size(min = 11, max = 11, message = "휴대폰 번호는 11자여야 합니다.")
        @NotBlank
        @Schema(description = "휴대폰 번호", example = "01012345555")
        String phone

) {
}
