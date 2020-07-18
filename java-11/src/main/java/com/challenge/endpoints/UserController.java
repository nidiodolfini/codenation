package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    User findById(@PathVariable("id") Long id) {
        return userService.findById(id).orElse(null);
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<User> findByUserByOptionalParameter(@RequestParam(value = "accelerationName", required = false) String acceleration,
//                                                    @RequestParam(value = "companyId", required = false) Long company) {
//        if (acceleration != null) {
//            return this.userService.findByAccelerationName(acceleration);
//        } else if (company != null) {
//            return this.userService.findByCompanyId(company);
//        } else return null;
//
//    }

    @GetMapping(params = "accelerationName")
    @ResponseStatus(HttpStatus.OK)
    List<User> findByAccelerationName(@RequestParam String accelerationName){
        return this.userService.findByAccelerationName(accelerationName);
    }

    @GetMapping(params = "companyId")
    @ResponseStatus(HttpStatus.OK)
    List<User> findByComanyId(@RequestParam Long companyId){
        return this.userService.findByCompanyId(companyId);
    }
}
