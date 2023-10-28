package com.liveshop.application.controller;

import com.liveshop.application.dto.UserDto;
import com.liveshop.application.exception.SingupException;
import com.liveshop.application.service.UserService;
import com.liveshop.application.utils.ResponseConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.liveshop.application.utils.ResponseConstants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/nickname/{nickname}")
    @ResponseBody
    public ResponseEntity<Void> checkDuplicateNickname(@PathVariable(name = "nickname")String nickname) {
        if(userService.isExistNickname(nickname)) {
            return FOUND;
        }
        else return OK;
    }

    @GetMapping("/id/{username}")
    @ResponseBody
    public ResponseEntity<Void> checkDuplicateUsername(@PathVariable(name = "username")String username) {
        if(userService.isExistUsername(username)) {
            return FOUND;
        }
        else return OK;
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Void> signup(@RequestBody UserDto signupUser) {
        try {
            userService.signup(signupUser);
            return CREATED;
        } catch (SingupException e) {
            return CONFLICT;
        }
    }
}


/**
 * 회원 가입 진행 시, 데이터가 생성 되면 CREATED를 날려주어야 한다.
 * validate는 Exeception을 던지게 해놓았다.
 * 그러면 Controller는 예외를 어떻게 처리하는 것이 깔끔할 것인가?
 *
 * 1. RestControllerAdvice
 * Advice를 시용하면 프록시 하나를 더 사용 하므로 전반적으로 연산이 전반적으로 더 많아짐.
 * 2. try catch
 * try catch로 분기 처리하면 자바는 Exception을 reflection으로 구현해놓았기 때문에 조건문 분기처리 보단 살짝 느리며 메모리 사용도 많아지나, 조건문과의 의존성을 낮출 수 있음.
 *
 */
