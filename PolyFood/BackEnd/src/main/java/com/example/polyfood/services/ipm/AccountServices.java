package com.example.polyfood.services.ipm;

import com.example.polyfood.models.Account;
import com.example.polyfood.models.Decentralization;
import com.example.polyfood.models.User;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.IAccountRepository;
import com.example.polyfood.repository.IDecentralizationRepository;
import com.example.polyfood.repository.IUserRepository;
import com.example.polyfood.services.IAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccountServices implements IAccountServices {
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IDecentralizationRepository decentralizationRepository;

    private Respon<Account> respon = new Respon<>();

    @Override
    public Respon<Account> signUp(String username, String password1, String password2, String email) {
        if (password1.equals(password2)){
            for (User user: userRepository.findAll()){
                if (user.getEmail().equals(email)){
                    respon.setMassage("email da ton tai");
                    return respon;
                }
            }
            Decentralization decentralization = decentralizationRepository.getReferenceById(3);
            Account account = new Account();
            User user = new User();
            Date date = new Date();
            account.setPassword(password1);
            account.setCreatedAt(date);
            account.setUpdateAt(date);
            account.setUserName(username);
            account.setDecentralization(decentralization);
            user.setEmail(email);
            user.setCreateAt(date);
            user.setUpdateAt(date);
            user.setAccount(account);
            accountRepository.save(account);
            userRepository.save(user);
            respon.setData(account);
            respon.setStatus(200);
            respon.setMassage("Them thanh cong");
        }
        else {
            respon.setMassage("mat khau khong khop " + password1 + " " + password2);
        }
        return respon;
    }
}
