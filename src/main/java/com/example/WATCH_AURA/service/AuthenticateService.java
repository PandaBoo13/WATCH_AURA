package com.example.WATCH_AURA.service;

import com.example.WATCH_AURA.dto.request.LoginRequest;
import com.example.WATCH_AURA.dto.respone.AuthenticateRespone;
import com.example.WATCH_AURA.exception.AppException;
import com.example.WATCH_AURA.exception.ErroCode;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

//@Service
//public class AuthenticateService {
//
//    @Autowired
//    AccountRepository accountRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    JwtService jwtService;
//
//
//
//    public AuthenticateRespone login(LoginRequest request) throws JOSEException {
//
//        var account = accountRepository.findByUsername(request.getUsername())
//                .orElseThrow(()-> new AppException(ErroCode.USER_EXISTED));
//        boolean authenticated= passwordEncoder.matches(request.getPassword(),account.getPassword());
//        if (!authenticated)throw  new AppException(ErroCode.UNAUTHENTICATED);
//
//        var token= jwtService.generateToken(request.getUsername());
//        System.out.println("Generated Token: " + token);
//        return AuthenticateRespone.builder()
//                .token(token)
//                .authenticated(true)
//                .build();
//
//    }
//}

@Service
@RequiredArgsConstructor
public class AuthenticateService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticateRespone login(LoginRequest request) {

        // 1. Spring Security sẽ tự gọi UserDetailsService và kiểm tra mật khẩu giúp bạn
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // 2. Nếu không ném exception → là hợp lệ
            String token = jwtService.generateToken(request.getUsername());

            return AuthenticateRespone.builder()
                    .token(token)
                    .authenticated(true)
                    .build();

        } catch (BadCredentialsException e) {
            // 3. Nếu username/password sai
            throw new AppException(ErroCode.UNAUTHENTICATED);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}

