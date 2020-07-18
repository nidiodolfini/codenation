package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.interfaces.CompanyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyServiceInterface companyService;

    @Autowired
    public CompanyController(CompanyServiceInterface companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Company findById(@PathVariable Long id) {
        return this.companyService.findById(id).orElse(null);
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    List<Company> findByAccelerationId(@RequestParam(required = false) Long accelerationId,
//                                       @RequestParam(required = false) Long userId) {
//        if (accelerationId != null) {
//            return this.companyService.findByAccelerationId(accelerationId);
//        } else if (userId != null){
//            return this.companyService.findByUserId(userId);
//        }else return null;
//    }

    @GetMapping(params = "accelerationId")
    @ResponseStatus(HttpStatus.OK)
    List<Company> findByAccelerationId(@RequestParam Long accelerationId){
        return this.companyService.findByAccelerationId(accelerationId);
    }

    @GetMapping(params = "userId")
    List<Company> findByUserId(@RequestParam Long userId){
        return this.companyService.findByUserId(userId);
    }

}
