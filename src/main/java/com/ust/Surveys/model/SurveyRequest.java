package com.ust.Surveys.model;

import java.util.List;

public class SurveyRequest {
    private Survey survey;
    private List<Integer> questionIds;

    // Getters and Setters
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
    }
}

