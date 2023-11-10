package com.liveshop.application.service;

import com.liveshop.application.domain.User;
import com.liveshop.application.dto.UserDto;
import com.liveshop.application.dto.request.LoginRequest;
import com.liveshop.application.exception.DuplicateException;
import com.liveshop.application.exception.SignupException;
import com.liveshop.application.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;
    private final Set<String> restAPIResources;

    public void signup(UserDto signupUser){
        try{
            validateSignup(signupUser);
            userMapper.insertUser(signupUser.toEntity());
        } catch (Exception e) {
            throw new SignupException();
        }
    }


    @Transactional(readOnly = true)
    public boolean isValidUsername(String username) {
        return !restAPIResources.contains(username) || userMapper.existByUsername(username);
    }

    @Transactional(readOnly = true)
    public boolean isValidNickname(String nickname) {
        return !restAPIResources.contains(nickname) || userMapper.existByNickname(nickname);

    }


    @Transactional(readOnly = true)
    public boolean login(LoginRequest loginRequest) {
        Optional<User> optionalUser = Optional.ofNullable(userMapper.findByUsername(loginRequest.getUsername()));
        if(optionalUser.isEmpty()){
            return false;
        }
        return optionalUser.get().getPassword().equals(loginRequest.getPassword());
    }

    private void validateSignup(UserDto signupUser) {
        if(isValidUsername(signupUser.getUsername()) || isValidNickname(signupUser.getNickname())) { // 단축평가.
            throw new DuplicateException();
        }
    }

}
