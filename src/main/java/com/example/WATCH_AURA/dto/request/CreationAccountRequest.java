package com.example.WATCH_AURA.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreationAccountRequest {
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String phonenumber;
    private String address;



}
