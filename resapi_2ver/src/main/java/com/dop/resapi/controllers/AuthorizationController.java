package com.dop.resapi.controllers;

import com.dop.resapi.autorization.JwtUtil;
import com.dop.resapi.models.User;
import com.dop.resapi.models.requests.LoginRequest;
import com.dop.resapi.models.response.LoginResponse;
import com.dop.resapi.models.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/rest/auth")
public class AuthorizationController {

    private final AuthenticationManager manager;
    private final JwtUtil jwt;

    public AuthorizationController(AuthenticationManager manager, JwtUtil jwt) {
        this.manager = manager;
        this.jwt = jwt;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequest loginReq) {
        try {
            Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));

            String email = auth.getName();
            User user = new User(email, "", "");
            String token = jwt.newToken(user);
            LoginResponse loginRes = new LoginResponse(email, token);

            return ResponseEntity.ok(loginRes);
        }
        catch(BadCredentialsException ex){
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "Недействительный логинили пароль.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        catch(Exception ex){
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
