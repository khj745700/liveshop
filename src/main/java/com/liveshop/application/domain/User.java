package com.liveshop.application.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor(access =  AccessLevel.PRIVATE)

@Getter
@Builder
public class User {
    @Id
    private Integer id;
    private String password;
    private String nickname;
    private String username;
    private String phoneNum;
}
