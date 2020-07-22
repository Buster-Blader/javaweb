package com.myproject.service;

import com.myproject.entity.AppUser;
import com.myproject.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public AppUser getUserByName(String name) {
        return appUserRepository.findByName(name);
    }
}
