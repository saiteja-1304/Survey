package com.ust.Surveys.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyFullResponse {
    //private String surveyId;
    private String domain;
    private String status;
    private String email;
    private String companyName;
    private String setName;
    //private List<Integer> questionIds;
    public ResponseSetDto setinfos;
}
