package com.myproject.service;

import com.myproject.entity.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    AppUser getUserByName(String name);


}
