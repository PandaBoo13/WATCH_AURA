package com.example.WATCH_AURA.controller;

import com.example.WATCH_AURA.dto.request.CreationAccountRequest;
import com.example.WATCH_AURA.dto.request.LoginRequest;
import com.example.WATCH_AURA.dto.respone.ApiRespone;
import com.example.WATCH_AURA.dto.respone.AuthenticateRespone;
import com.example.WATCH_AURA.entity.Account;
import com.example.WATCH_AURA.service.AccountService;
import com.example.WATCH_AURA.service.AuthenticateService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authen")
public class AuthenticateController {
    @Autowired
    private AuthenticateService authenticateService;
    @Autowired
    private AccountService accountService;
    @PostMapping("/log-in")
    ApiRespone<AuthenticateRespone> login(@RequestBody LoginRequest request) throws JOSEException {
        var login= authenticateService.login(request);
        return ApiRespone.<AuthenticateRespone>builder()
                .success(true)
                .data(login)
                .build();

    }

    @PostMapping("/register")
    public ApiRespone<Account> createAccount(@RequestBody CreationAccountRequest request){
        Account account=accountService.createAccount(request);
        ApiRespone<Account> apiRespone= ApiRespone.<Account>builder()
                .success(true)
                .message("Create new account successfully")
                .data(account)
                .build();
        return apiRespone;
    }

}
