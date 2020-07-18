package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acceleration")
public class AccelerateController {

    private final AccelerationServiceInterface accelerationService;

    @Autowired
    public AccelerateController(AccelerationServiceInterface accelerationService) {
        this.accelerationService = accelerationService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Acceleration findById(@PathVariable Long id){
        return this.accelerationService.findById(id).orElse(null);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Acceleration> findByCompanyId(@RequestParam Long companyId){
        return this.accelerationService.findByCompanyId(companyId);
    }
}
