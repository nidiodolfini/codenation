package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    private final SubmissionServiceInterface submissionService;

    @Autowired
    public SubmissionController(SubmissionServiceInterface submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<SubmissionDTO> findByChallengeIdAndAccelerationId(@RequestParam Long challengeId,
                                                           @RequestParam Long accelerationId){
        SubmissionMapper mapper = Mappers.getMapper(SubmissionMapper.class);
        return mapper.map(this.submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId));

    }
}
