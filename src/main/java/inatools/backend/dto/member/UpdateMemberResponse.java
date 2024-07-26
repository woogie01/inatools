package inatools.backend.dto.member;

import inatools.backend.domain.Member;

public record UpdateMemberResponse(
        String name,
        String userId,
        String password,
        String phone
) {
    public static UpdateMemberResponse fromMember(Member member) {
        return new UpdateMemberResponse(
                member.getName(),
                member.getUserId(),
                member.getPassword(),
                member.getPhone()
        );
    }
}
