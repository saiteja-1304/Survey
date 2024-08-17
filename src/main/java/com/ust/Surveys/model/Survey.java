package com.ust.Surveys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long surveyId;
    private String domain;
    private String status;
    private String email;
    private String companyName;
    private String setName;
   // private List<Integer> questionIds;
   @Convert(converter = ListToJsonConverter.class)
   //@OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Integer> questionIds = new ArrayList<>();
}
