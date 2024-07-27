package inatools.backend.dto;

import inatools.backend.domain.Member;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateMemberRequest(
        @NotNull Long id,
        @NotEmpty String name,
        @NotEmpty String userId,
        @NotEmpty String password,
        @NotEmpty String phone

) {
}
