package com.example.WATCH_AURA.service;

import com.example.WATCH_AURA.dto.request.CreationAccountRequest;
import com.example.WATCH_AURA.entity.Account;
import com.example.WATCH_AURA.entity.User;
import com.example.WATCH_AURA.exception.AppException;
import com.example.WATCH_AURA.exception.ErroCode;
import com.example.WATCH_AURA.repository.AccountRepository;
import com.example.WATCH_AURA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;


    public Account createAccount(CreationAccountRequest request){
        if(accountRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErroCode.USER_EXISTED);
        }
        Account account= new Account();
        account.setUsername(request.getUsername());
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        account.setPassword(encodedPassword);
        account.setRole("USER");
        accountRepository.save(account);

        User user= new User();
        user.setUserId(account.getAccountId());
        user.setEmail(request.getEmail());
        user.setFullname(request.getFullname());
        user.setPhonenumber(request.getPhonenumber());
        user.setAddress(request.getAddress());
        userRepository.save(user);

        return account;

    }

    public Account getAccountByUserName(String username){
        Account account= new Account();
        account=accountRepository.findByUsername(username).orElseThrow(()-> new AppException(ErroCode.USER_NOT_EXISTS));
        return account;
    }


}
