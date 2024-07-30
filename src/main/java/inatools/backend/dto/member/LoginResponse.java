package inatools.backend.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record LoginResponse(

        @Schema(description = "user index", example = "1")
        Long id,

        @Schema(description = "username", example = "pilho")
        String username,

        @Schema(description = "userId", example = "pil0602")
        String userId,

        @Schema(description = "access token", example = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NSwiaWF0Ijox123456wMDM5LCJleHAiOjE3MDAyNzIwMzl9.0mjwFO6JtabcdefgrPgGm-2XWY60G2YH6HbTwU386Po")
        String accessToken,

        @Schema(description = "refresh token", example = "eyABCDciOiJIUzI1NiJ9.abcdefg6NSwiaWF0IjoxNjk3NjgwMDM5LCJleHAiOjE3Mxyz987wMzl9.0mjwFO6Jtqz616yprPgGm-2XWY60G2YH6HbTwU123q0")
        String refreshToken
) {
}