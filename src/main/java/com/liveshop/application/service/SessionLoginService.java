package com.liveshop.application.service;

import com.liveshop.application.domain.User;
import com.liveshop.application.dto.request.LoginRequest;
import com.liveshop.application.mapper.UserMapper;
import com.liveshop.application.utils.SessionConstants;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Spring에는 5가지의 빈 스코프가 존재한다.
 * <ol>
 *     <li>singleton</li>
 *          - 기본 스코프로서, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위 스코프
 *     <li>prototype</li>
 *          - 프로토타입 빈의 생성과 의존관계 주입까지만 하고 더이상 관리하지 않는 매우 짧은 범위의 스코프
 *     <li>request</li>
 *          - 웹 요청이 들어오고 나갈 때까지 유지되는 스코프
 *     <li>session</li>
 *          - 웹 세션이 생성되고 종료될 때까지 유지되는 스코프
 * </ol>
 *
 * 기본적인 Component Annotation은 singleton 빈 스코프로 유지가 되나, HttpSession은  Session Scope로 생성된 빈이 주입이 되기 때문에
 * 각각의 사용자마다 본인의 세션이 동작하도록 한다.
 * 하지만 서로 다른 스코프 범위 때문에, HttpSession 인스턴스는 동적 프록시로 생성되어 주입이 된다.
 * 이러한 기법을 Scoped Proxy라고 한다.
 *
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class SessionLoginService implements LoginService{
    private final UserMapper userMapper;
    private final HttpSession session;
    @Transactional(readOnly = true)
    public boolean login(LoginRequest loginRequest) {
        Optional<User> optionalUser = Optional.ofNullable(userMapper.findByUsername(loginRequest.getUsername()));
        if(optionalUser.isEmpty()){
            setUserSession(loginRequest.getUsername());
            return false;
        }
        return optionalUser.get().getPassword().equals(loginRequest.getPassword());
    }

    @Override
    public void logout() {
        log.trace("remove session: {}", session.getAttribute(SessionConstants.USER_ID));
        session.removeAttribute(SessionConstants.USER_ID);
    }

    @Override
    public String getCurrentUser() {

        return (String) session.getAttribute(SessionConstants.USER_ID);
    }

    private void setUserSession(String username) {
        session.setAttribute(SessionConstants.USER_ID, username);
        log.trace("create session: {}", session.getAttribute(SessionConstants.USER_ID));
    }
}
