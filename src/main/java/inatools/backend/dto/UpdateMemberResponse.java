package inatools.backend.dto;

import inatools.backend.domain.Member;

public record UpdateMemberResponse(
        Long id,
        String username,
        String userId,
        String password,
        String phone
) {
    public static UpdateMemberResponse fromMember(Member member) {
        return new UpdateMemberResponse(
                member.getId(),
                member.getUsername(),
                member.getUserId(),
                member.getPassword(),
                member.getPhone()
        );
    }
}
