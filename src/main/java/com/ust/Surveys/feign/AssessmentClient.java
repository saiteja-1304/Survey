package com.ust.Surveys.feign;

import com.ust.Surveys.client.ResponseSetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Assessment", url = "http://localhost:2545/assessment")
public interface AssessmentClient {
//    @GetMapping("/{setname}")
//    ResponseEntity<SurveySetInfo> getAssessmentBySetName(@PathVariable String setname);
//@GetMapping("/{setname}")
// List<SurveySetInfo> getAssessmentBySetName(@PathVariable String setname);

 @GetMapping("/set/{setname}")
 ResponseSetDto getAssessmentBySetName(@PathVariable String setname);
}
