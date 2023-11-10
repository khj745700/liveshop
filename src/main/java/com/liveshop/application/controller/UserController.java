package com.liveshop.application.controller;

import com.liveshop.application.dto.UserDto;
import com.liveshop.application.dto.request.LoginRequest;
import com.liveshop.application.exception.SignupException;
import com.liveshop.application.service.UserService;
import com.liveshop.application.utils.ResponseConstants;
import com.liveshop.application.utils.SessionConstants;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.liveshop.application.utils.ResponseConstants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/nickname/{nickname}")
    @ResponseBody
    public ResponseEntity<Void> checkIsValidNickname(@PathVariable(name = "nickname")String nickname) {
        if(userService.isValidNickname(nickname)) {
            return FOUND;
        }
        else return OK;
    }

    @GetMapping("/id/{username}")
    @ResponseBody
    public ResponseEntity<Void> checkIsValidUsername(@PathVariable(name = "username")String username) {
        if(userService.isValidUsername(username)) {
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
        } catch (SignupException e) {
            return CONFLICT;
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, @NonNull HttpSession session) {
        if(userService.login(loginRequest)){
            session.setAttribute(SessionConstants.SESSION_NAME, loginRequest.getUsername());
            log.trace("create session: {}", session.getAttribute(SessionConstants.SESSION_NAME));
            return ACCEPTED;
        }
        return UNAUTHORIZED;
    }

    @DeleteMapping("/login")
    @ResponseBody
    public ResponseEntity<Void> logout(@NonNull HttpSession session) {
            log.trace("remove session: {}", session.getAttribute(SessionConstants.SESSION_NAME));
            session.removeAttribute(SessionConstants.SESSION_NAME);
            return OK;
    }
}

/**
 로그레벨은 TRACE > DEBUG > INFO > WARN > ERROR > FATAL 순
 TRACE :추적 레벨은 Debug보다 좀더 상세한 정보를 나타냄
 DEBUG : 프로그램을 디버깅하기 위한 정보 지정
 INFO : 상태변경과 같은 정보성 메시지를 나타냄
 WARN : 처리 가능한 문제, 향후 시스템 에러의 원인이 될 수 있는 경고성 메시지를 나타냄
 ERROR : 요청을 처리하는 중 문제가 발생한 경우
 FATAL : 아주 심각한 에러가 발생한 상태, 시스템적으로 심각한 문제가 발생해서 어플리케이션 작동이 불가능할 경우
 */


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
