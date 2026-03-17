package com.project.uber.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class JwtReq {
    String username;
    String password;
}
