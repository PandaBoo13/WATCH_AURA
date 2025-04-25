package com.example.WATCH_AURA.controller;

import com.example.WATCH_AURA.dto.request.CreationAccountRequest;
import com.example.WATCH_AURA.dto.respone.ApiRespone;
import com.example.WATCH_AURA.entity.Account;
import com.example.WATCH_AURA.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AcountController {
    @Autowired
    private AccountService accountService;


  @GetMapping("/{username}")
   public ApiRespone<Account> getAccountByUserName(@PathVariable("username") String username ){
      Account account= accountService.getAccountByUserName(username);
      return ApiRespone.<Account>builder()
              .success(true)
              .message("Get successful")
              .data(account)
              .build();
  }
}
