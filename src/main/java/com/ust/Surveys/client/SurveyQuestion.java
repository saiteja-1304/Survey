package com.ust.Surveys.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyQuestion {
    private Integer questionId;
    private String questionText;
    private List<SurveyAnswer> answers;
}
