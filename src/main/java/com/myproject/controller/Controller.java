package com.myproject.controller;

import com.myproject.entity.AppUser;
import com.myproject.repositories.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    AppUserRepository appUserRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Cacheable(value = "users",key = "#id",unless = "#result.userId==2")
    @GetMapping("/api/user/{id}")
    public AppUser process(@PathVariable("id") Long id){
        //   HttpHeaders httpHeaders=new HttpHeaders();
        // httpHeaders.set("he llo","greet");
        LOG.info("Getting user with ID {}.", id);
        return appUserRepository.findOne(id);
    }
}
