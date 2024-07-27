package inatools.backend.dto.member;

import inatools.backend.domain.Member;

public record MemberInfoResponse(
        Long id,
        String name,
        String userId,
        String email,
        String phone
) {
    public static MemberInfoResponse fromMember(Member member) {
        return new MemberInfoResponse(
                member.getId(),
                member.getName(),
                member.getUserId(),
                member.getEmail(),
                member.getPhone()
        );
    }
}
