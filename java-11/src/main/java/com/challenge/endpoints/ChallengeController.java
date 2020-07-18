package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeServiceInterface challengeService;

    @Autowired
    public ChallengeController(ChallengeServiceInterface challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
        List<Challenge> findByAccelerationIdAndUserId(@RequestParam Long accelerationId,
                                                      @RequestParam Long userId){
        return this.challengeService.findByAccelerationIdAndUserId(accelerationId, userId);
    }
}
