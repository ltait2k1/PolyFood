package com.example.polyfood.controllers;

import com.example.polyfood.models.Account;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.services.IAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/version1.0")
public class AccountController {
    @Autowired
    private IAccountServices accountServices;

    @PostMapping(value = "account/signup")
    public Respon<Account> SignUp(@RequestParam String username, String password1, String password2, String email){
        return accountServices.signUp(username,password1,password2,email);
    }

}
