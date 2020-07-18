package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.interfaces.CandidateServiceInterface;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateServiceInterface candidateService;
    private CandidateMapper mapper = Mappers.getMapper(CandidateMapper.class);

    @Autowired
    public CandidateController(CandidateServiceInterface candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    CandidateDTO findById(@PathVariable Long userId,
                          @PathVariable Long accelerationId,
                          @PathVariable Long companyId) {
        return mapper.map(this.candidateService.findById(userId, companyId, accelerationId).orElse(null));
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    List<CandidateDTO> findByAccelerationIdOrByCompanyId(@RequestParam(required = false) Long accelerationId,
//                                                         @RequestParam(required = false) Long companyId){
//        CandidateMapper mapper = Mappers.getMapper(CandidateMapper.class);
//        if(accelerationId != null){
//            return  mapper.map(this.candidateService.findByAccelerationId(accelerationId));
//        }else if( companyId != null){
//            return  mapper.map(this.candidateService.findByCompanyId(companyId));
//        }else return null;
//    }

    @GetMapping(params = "accelerationId")
    @ResponseStatus(HttpStatus.OK)
    List<CandidateDTO> findByAccelerationId(@RequestParam Long accelerationId) {
        return mapper.map(this.candidateService.findByAccelerationId(accelerationId));
    }

    @GetMapping(params = "companyId")
    @ResponseStatus(HttpStatus.OK)
    List<CandidateDTO> findByCompanyId(@RequestParam Long companyId){
        return mapper.map(this.candidateService.findByCompanyId(companyId));
    }

}
