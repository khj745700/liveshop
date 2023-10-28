package com.liveshop.application.dto;

import com.liveshop.application.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.beans.Encoder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto {
    private String username;
    private String nickname;
    private String phoneNum;
    private String password;


    public User toEntity() {
        return User.builder()
                .nickname(nickname)
                .password(password)
                .phoneNum(phoneNum)
                .username(username)
                .build();
    }
}
