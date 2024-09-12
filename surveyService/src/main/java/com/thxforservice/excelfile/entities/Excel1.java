package com.thxforservice.excelfile.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Excel1 {

    private Long id;  // Auto-generated ID

    private Long num;

    private String q;

}
