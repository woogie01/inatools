package inatools.backend.dto.member;

import inatools.backend.domain.Member;

public record SignUpResponse(
        Long id,
        String username,
        String userId,
        String password,
        String email,
        String phone
) {
    public static SignUpResponse fromMember(Member member) {
        return new SignUpResponse(
                member.getId(),
                member.getUsername(),
                member.getUserId(),
                member.getPassword().getValue(),
                member.getEmail(),
                member.getPhone()
        );
    }
}
