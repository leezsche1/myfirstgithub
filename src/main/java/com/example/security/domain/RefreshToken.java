package com.example.security.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "refresh_token")
@NoArgsConstructor
@Getter
@Setter
public class RefreshToken {

    @Id
    @GeneratedValue
    private Long id;
    private Long memberId;
    private String value;

}
