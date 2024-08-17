package com.ust.Surveys.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestSurveyDto {
    private String domain;
    private String email;
    private String companyName;
    private String setName;
    private List<Integer> questionIds = new ArrayList<>();

}
