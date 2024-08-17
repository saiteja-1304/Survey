package com.ust.Surveys.controller;

import com.ust.Surveys.client.SurveyFullResponse;
import com.ust.Surveys.feign.AssessmentClient;
import com.ust.Surveys.model.RequestSurveyDto;
import com.ust.Surveys.model.Survey;
import com.ust.Surveys.model.SurveyRequest;
import com.ust.Surveys.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/survey")

public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private AssessmentClient assessmentFeignClient;


//    @PostMapping
//    public SurveyFullResponse addSurvey(@RequestBody Survey survey, @RequestBody ArrayList<Integer> questionIds) {
//        return surveyService.addSurvey(survey,questionIds);
//    }

    @PostMapping
    public SurveyFullResponse addSurvey(@RequestBody RequestSurveyDto surveydto) {
        Survey survey =new Survey();
        survey.setSetName(surveydto.getSetName());
        survey.setEmail(surveydto.getEmail());
        survey.setDomain(surveydto.getDomain());
        survey.setQuestionIds(surveydto.getQuestionIds());
        survey.setCompanyName(surveydto.getCompanyName());
        return surveyService.addSurvey(survey);
    }


    @GetMapping("/{surveyId}")
    public SurveyFullResponse getSurvey(@PathVariable Long surveyId) {
        return surveyService.getSurvey(surveyId);
    }

    @GetMapping("/all")
    public List<SurveyFullResponse> getAllSurveys() {
        return surveyService.getAllSurveys();
    }
}
