package com.thxforservice.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thxforservice.survey.controllers.RequestSurvey;
import com.thxforservice.survey.services.SurveySaveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
public class DataTransfer {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SurveySaveService saveService;

    @Test
    void transfer() throws Exception {
        List<RequestSurvey> items = om.readValue(new File("D:/data/survey.json"), new TypeReference<>() {});

        for (RequestSurvey item : items) {
            saveService.save(item);
        }
    }
}
