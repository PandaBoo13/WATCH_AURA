package com.example.WATCH_AURA.service;

import com.example.WATCH_AURA.entity.Account;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.support.JettyHeadersAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
@Service
public class JwtService {

    private long jwtExpiration;
    @NonFinal
    private String signerKey = "PzcEXHkagVn34ElrlcOUQLazHeIg86xNVPk5Fj94TzH0RyCLngqSyPmzUtWc7yyP";

    private final AccountService accountService;

    public JwtService(AccountService accountService) {
        this.accountService = accountService;
    }


    public String generateToken(String username) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("watch_aura")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(10, ChronoUnit.MINUTES).toEpochMilli()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        jwsObject.sign(new MACSigner(signerKey.getBytes()));
        return jwsObject.serialize();
    }


    public Account validateTokenAndGetUser(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);

            if (!jwsObject.verify(new MACVerifier(signerKey.getBytes()))) return null;

            JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());

            String username = claims.getSubject();
            Date expiration = claims.getExpirationTime();

            if (username == null || expiration.before(new Date())) return null;

            return accountService.getAccountByUserName(username);

        } catch (Exception e) {
            return null;
        }
    }
}
