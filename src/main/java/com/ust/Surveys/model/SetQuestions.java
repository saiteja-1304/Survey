package com.ust.Surveys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionId;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
