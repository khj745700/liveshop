package com.liveshop.application.service;

import com.liveshop.application.dto.UserDto;
import com.liveshop.application.exception.DuplicateException;
import com.liveshop.application.exception.SingupException;
import com.liveshop.application.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    @Transactional
    public void signup(UserDto signupUser) throws SingupException {
        validateSignup(signupUser);
        userMapper.insertUser(signupUser.toEntity());
    }

    @Transactional(readOnly = true)
    public boolean isExistUsername(String username) {
        return userMapper.existByUsername(username);
    }

    @Transactional(readOnly = true)
    public boolean isExistNickname(String nickname) {
        return userMapper.existByNickname(nickname);
    }

    private void validateSignup(UserDto signupUser) {
        if(isExistUsername(signupUser.getUsername())) {
            throw new DuplicateException();
        }

        if(isExistNickname(signupUser.getNickname())){
            throw new DuplicateException();
        }
    }
}
