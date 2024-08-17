package com.ust.Surveys.service;

import com.ust.Surveys.ExceptionsHandling.QuestionNotFoundException;
import com.ust.Surveys.ExceptionsHandling.SurveyNotFoundException;
import com.ust.Surveys.client.SurveyFullResponse;
import com.ust.Surveys.client.ResponseSetDto;
import com.ust.Surveys.client.SurveyQuestion;
import com.ust.Surveys.feign.AssessmentClient;
import com.ust.Surveys.model.Survey;
import com.ust.Surveys.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private AssessmentClient assessmentClient;



//    public static class SurveyNotFoundException extends RuntimeException {
//        public SurveyNotFoundException(String message) {
//            super(message);
//        }
//    }

//    public static class QuestionNotFoundException extends RuntimeException {
//        public QuestionNotFoundException(String message) {
//            super(message);
//        }
//    }

//    public SurveyFullResponse addSurvey(Survey survey, List<Integer> ques) {
//            //Survey survey1=survey.get();
//        Survey survey1=surveyRepository.save(survey);
//        //List<Integer> questionIds=ques;
//            ResponseSetDto setInfos=assessmentClient.getAssessmentBySetName(survey1.getSetName());
//            //setInfos.setQuestions(survey.getQuestions());
//            SurveyFullResponse res= new SurveyFullResponse();
//            res.setSetName(survey1.getSetName());
//            res.setEmail(survey1.getEmail());
//            res.setDomain(survey1.getDomain());
//            res.setStatus(survey1.getStatus());
//            res.setCompanyName(survey1.getCompanyName());
//            //res.setQuestionIds(ques);
//            List<SurveyQuestion> questions= new ArrayList<>();
//            for (Integer questionId : ques) {
//                List<SurveyQuestion> questionlist=setInfos.getQuestions();
//                for(SurveyQuestion question:questionlist){
//                    if(question.getQuestionId()==questionId){
//                        questions.add(question);
//                    }
//                }
//            }
//            setInfos.setQuestions(questions);
//            res.setSetinfos(setInfos);
//            return res;
//    }


    public SurveyFullResponse MapQuestions(Survey survey) {
        List<Integer> ques=survey.getQuestionIds();
        // Check if survey is null
        if (survey == null) {
            throw new SurveyNotFoundException("Survey cannot be null");
        }
        //survey.setQuestionIds(ques);
        // Save the survey to the repository
        Survey survey1 = surveyRepository.save(survey);

        // Fetch assessment set info from the client
        ResponseSetDto setInfos = assessmentClient.getAssessmentBySetName(survey1.getSetName());
        if (setInfos == null) {
            throw new SurveyNotFoundException("Assessment set not found for setName: " + survey1.getSetName());
        }

        // Initialize response object
        SurveyFullResponse res = new SurveyFullResponse();
        res.setSetName(survey1.getSetName());
        res.setEmail(survey1.getEmail());
        res.setDomain(survey1.getDomain());
        res.setStatus(survey1.getStatus());
        res.setCompanyName(survey1.getCompanyName());

        // Process the questions
        List<SurveyQuestion> questions = new ArrayList<>();
        List<SurveyQuestion> questionlist = setInfos.getQuestions();

        if (questionlist == null) {
            throw new SurveyNotFoundException("No questions found for the assessment set");
        }

        for (Integer questionId : ques) {
            Optional<SurveyQuestion> questionOpt = questionlist.stream()
                    .filter(question -> question.getQuestionId().equals(questionId))
                    .findFirst();

            if (!questionOpt.isPresent()) {
                throw new QuestionNotFoundException(questionId);
            }

            questions.add(questionOpt.get());
        }

        // Set the questions and return the response
        setInfos.setQuestions(questions);
        res.setSetinfos(setInfos);

        return res;
    }

    public SurveyFullResponse addSurvey(Survey survey) {
        SurveyFullResponse res = MapQuestions(survey);

//        List<Integer> ques=survey.getQuestionIds();
//        // Check if survey is null
//        if (survey == null) {
//            throw new SurveyNotFoundException("Survey cannot be null");
//        }
//        //survey.setQuestionIds(ques);
//        // Save the survey to the repository
//        Survey survey1 = surveyRepository.save(survey);
//
//        // Fetch assessment set info from the client
//        ResponseSetDto setInfos = assessmentClient.getAssessmentBySetName(survey1.getSetName());
//        if (setInfos == null) {
//            throw new SurveyNotFoundException("Assessment set not found for setName: " + survey1.getSetName());
//        }
//
//        // Initialize response object
//        SurveyFullResponse res = new SurveyFullResponse();
//        res.setSetName(survey1.getSetName());
//        res.setEmail(survey1.getEmail());
//        res.setDomain(survey1.getDomain());
//        res.setStatus(survey1.getStatus());
//        res.setCompanyName(survey1.getCompanyName());
//
//        // Process the questions
//        List<SurveyQuestion> questions = new ArrayList<>();
//        List<SurveyQuestion> questionlist = setInfos.getQuestions();
//
//        if (questionlist == null) {
//            throw new SurveyNotFoundException("No questions found for the assessment set");
//        }
//
//        for (Integer questionId : ques) {
//            Optional<SurveyQuestion> questionOpt = questionlist.stream()
//                    .filter(question -> question.getQuestionId().equals(questionId))
//                    .findFirst();
//
//            if (!questionOpt.isPresent()) {
//                throw new QuestionNotFoundException(questionId);
//            }
//
//            questions.add(questionOpt.get());
//        }
//
//        // Set the questions and return the response
//        setInfos.setQuestions(questions);
//        res.setSetinfos(setInfos);

        return res;
    }





//    public SurveyFullResponse getSurvey(String surveyId) {
//        Optional<Survey> survey = surveyRepository.findById(surveyId);
//        if(survey.isPresent()){
//            Survey survey1=survey.get();
//            ResponseSetDto setInfos=assessmentClient.getAssessmentBySetName(survey1.getSetName());
//            SurveyFullResponse res= new SurveyFullResponse();
//            res.setSetName(survey1.getSetName());
//            res.setEmail(survey1.getEmail());
//            res.setDomain(survey1.getDomain());
//            res.setStatus(survey1.getStatus());
//            res.setCompanyName(survey1.getCompanyName());
//            res.setSetinfos(setInfos);
//            return res;
//        }
//        return null;
//    }


    public SurveyFullResponse getSurvey(Long surveyId) {
        Optional<Survey> survey = surveyRepository.findById(String.valueOf(surveyId));
        if (survey.isEmpty()) {
            throw new SurveyNotFoundException("survey with " +surveyId+ " not found"); // Ensure this exception is defined
        }
        Survey survey1 = survey.get();
        //if (survey.isPresent()) {
//            Survey survey1 = survey.get();
//            ResponseSetDto setInfos = assessmentClient.getAssessmentBySetName(survey1.getSetName());
//            SurveyFullResponse res = new SurveyFullResponse();
//            res.setSetName(survey1.getSetName());
//            res.setEmail(survey1.getEmail());
//            res.setDomain(survey1.getDomain());
//            res.setStatus(survey1.getStatus());
//            res.setCompanyName(survey1.getCompanyName());
//            res.setSetinfos(setInfos);
//            return res;


            List<Integer> ques=survey.get().getQuestionIds();
            // Check if survey is null
            if (survey == null) {
                throw new SurveyNotFoundException("Survey cannot be null");
            }
            //survey.setQuestionIds(ques);
            // Save the survey to the repository
            //Survey survey1 = surveyRepository.save(survey);

            // Fetch assessment set info from the client
            ResponseSetDto setInfos = assessmentClient.getAssessmentBySetName(survey1.getSetName());
            if (setInfos == null) {
                throw new SurveyNotFoundException("Assessment set not found for setName: " + survey1.getSetName());
            }

            // Initialize response object
            SurveyFullResponse res = new SurveyFullResponse();
            res.setSetName(survey1.getSetName());
            res.setEmail(survey1.getEmail());
            res.setDomain(survey1.getDomain());
            res.setStatus(survey1.getStatus());
            res.setCompanyName(survey1.getCompanyName());

            // Process the questions
            List<SurveyQuestion> questions = new ArrayList<>();
            List<SurveyQuestion> questionlist = setInfos.getQuestions();

            if (questionlist == null) {
                throw new SurveyNotFoundException("No questions found for the assessment set");
            }

            for (Integer questionId : ques) {
                Optional<SurveyQuestion> questionOpt = questionlist.stream()
                        .filter(question -> question.getQuestionId().equals(questionId))
                        .findFirst();

                if (!questionOpt.isPresent()) {
                    throw new QuestionNotFoundException(questionId);
                }

                questions.add(questionOpt.get());
            }

            // Set the questions and return the response
            setInfos.setQuestions(questions);
            res.setSetinfos(setInfos);

            return res;

        }
        //throw new SurveyNotFoundException("survey with " +surveyId+ " not found"); // Throw custom exception if survey is not found




    public List<SurveyFullResponse> getAllSurveys() {
        List<SurveyFullResponse> fullResponses=new ArrayList<>();
        List<Survey> surveys = surveyRepository.findAll();
        for (Survey survey : surveys) {
//            ResponseSetDto setInfos = assessmentClient.getAssessmentBySetName(survey.getSetName());
//            SurveyFullResponse res = new SurveyFullResponse();
//            res.setSetName(survey.getSetName());
//            res.setEmail(survey.getEmail());
//            res.setDomain(survey.getDomain());
//            res.setStatus(survey.getStatus());
//            res.setCompanyName(survey.getCompanyName());
//            res.setSetinfos(setInfos);
//            fullResponses.add(res);
            SurveyFullResponse res=MapQuestions(survey);
            fullResponses.add(res);
        }
        return fullResponses;
    }
}
