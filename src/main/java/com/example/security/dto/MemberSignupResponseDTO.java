package com.example.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberSignupResponseDTO {
    private Long memberId;
    private String email;
    private String name;
    private LocalDateTime regdate;
}
