package com.eMart.main.service;

import com.eMart.main.models.AccountDetails;
import com.eMart.main.models.MyUserDetails;
import com.eMart.main.repository.AccountDetailsRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountDetailsRepositry accountDetailsRepositry;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<AccountDetails> user=accountDetailsRepositry.findByEmployeeDetailsEmail(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return new MyUserDetails(user.get());
    }
}