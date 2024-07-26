package inatools.backend.dto.member;

import inatools.backend.domain.Member;

public record SignUpResponse(
        Long id,
        String name,
        String userId,
        String password,
        String email,
        String phone
) {
    public static SignUpResponse fromMember(Member member) {
        return new SignUpResponse(
                member.getId(),
                member.getName(),
                member.getUserId(),
                member.getPassword(),
                member.getEmail(),
                member.getPhone()
        );
    }
}
