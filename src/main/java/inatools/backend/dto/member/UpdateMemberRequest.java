package inatools.backend.dto.member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateMemberRequest(
        @NotEmpty String name,
        @NotEmpty String userId,
        @NotEmpty String password,
        @NotEmpty String phone

) {
}
