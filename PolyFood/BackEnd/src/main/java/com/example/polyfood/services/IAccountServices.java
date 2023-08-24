package com.example.polyfood.services;

import com.example.polyfood.models.Account;
import com.example.polyfood.models.responobj.Respon;

public interface IAccountServices {
    public Respon<Account> signUp(String username, String password1, String password2, String email);
}
