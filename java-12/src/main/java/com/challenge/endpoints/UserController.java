package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@EnableResourceServer
public class UserController {

    private UserService service;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return service.findById(id).orElse(null);
    }

    @GetMapping("/user")
    public List<User> findAll(@RequestParam(required = false) Optional<String> accelerationName,
                              @RequestParam(required = false) Optional<Long> companyId) {
        return accelerationName.map(service::findByAccelerationName)
                .orElseGet(() -> companyId.map(service::findByCompanyId).orElse(new ArrayList<>()));
    }

}
