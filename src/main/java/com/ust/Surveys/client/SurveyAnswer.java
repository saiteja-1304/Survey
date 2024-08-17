package com.ust.Surveys.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyAnswer {
    private Integer answerId;
    private String answer;
    private String suggestion;
}
