package com.liveshop.application.service;


import com.liveshop.application.dto.request.LoginRequest;

public interface LoginService {

    boolean login(LoginRequest loginRequest);

    void logout();

    String getCurrentUser();
}
