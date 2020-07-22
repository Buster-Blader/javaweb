package com.myproject.controller;

import com.myproject.entity.AppUser;
import com.myproject.entity.productdb.Product;
import com.myproject.repositories.AppUserRepository;
import com.myproject.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DefaultController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductService productService;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

//    @Cacheable(value = "products",key = "#productId",unless = "#result.productID > 1002)
//    @GetMapping("/api/{id}")
//    public ResponseEntity<Product> findById(@PathVariable("id") Long id){
//        return ResponseEntity.ok(productService.findById(id));
//    }

//    @GetMapping("/api/user/{id}")
//    ResponseEntity<AppUser> findUserById(@PathVariable("id") String id){
//        return ResponseEntity.ok(appUserRepository.findByName(id));
//    }

    @Cacheable(value = "users",key = "#id",unless = "#result.userId==2")
    @GetMapping("/api/user/{id}")
    public AppUser process(@PathVariable("id") Long id){
     //   HttpHeaders httpHeaders=new HttpHeaders();
       // httpHeaders.set("he llo","greet");
        LOG.info("Getting user with ID {}.", id);
        return appUserRepository.findOne(id);
    }
}
