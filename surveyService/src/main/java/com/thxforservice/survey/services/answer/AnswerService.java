package com.thxforservice.survey.services.answer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.survey.controllers.RequestAnswer;
import com.thxforservice.survey.entities.SurveyQuestion;
import com.thxforservice.survey.repositories.SurveyQuestionRepository;
import com.thxforservice.survey.repositories.SurveyResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnswerService {

private final SurveyResultRepository sResultRepository;
private final SurveyQuestionRepository sQuestionRepository;
private final MemberUtil memberUtil;
private ObjectMapper objectMapper;

    public void process(RequestAnswer form) {
        /*
        if (!memberUtil.isLogin()) {
            throw new MemberNotFoundException(); // Make sure this exception is defined
        }

        String mode = form.getMode();
        mode = StringUtils.hasText(mode) ? mode.trim() : "write";
        System.out.println("form:" + form);

        // Check if the user is logged in


        SurveyResult data;
        Long prgrsNo = form.getPrgrsNo();
        Long srvyNo = form.getSrvyNo (); //
        if (prgrsNo != null && mode.equals("update")) { // Update existing survey result
            // Fetch existing survey result for updating
            data = sResultRepository.findById(prgrsNo)
                    .orElseThrow(SurveyResultNotFoundException::new);
            System.out.println("여기 : " + data);
        } else { // New submission
            data = new SurveyResult();


            SurveyInfo surveyInfo = sQuestionRepository.findBySrvyNo(srvyNo) .orElseThrow(() -> new SurveyQuestionNotFoundException("Survey not found for id: " + srvyNo));
            data.setSurveyInfo(surveyInfo);


            // Fetch questions for the survey
            List<SurveyQuestion> questions = sQuestionRepository.findBySurveyInfo(surveyInfo);

            // Process answerData to calculate total score if needed
            int totalScore = calculateTotalScore(form.getAnswerData(), questions);
            form.setTotScr((long) totalScore); // Set total score in form

        }

        // Setting common fields for both new and existing survey results
        data.setStudentNo(form.getStudentNo());
        data.setEmail(form.getEmail());
        data.setUsername(form.getUsername());
        data.setAnswerData(form.getAnswerData());
        data.setTotScr(form.getTotScr());

        // Save and flush the survey result to the database
        sResultRepository.saveAndFlush(data);

         */
}

    private int calculateTotalScore(String answerData, List<SurveyQuestion> questions) {
        int totalScore = 0;

        try {
            // Parse answerData JSON string into a Map
            Map<String, Integer> answers = objectMapper.readValue(answerData, new TypeReference<Map<String, Integer>>() {});

            // Iterate over the questions
            for (SurveyQuestion question : questions) {
                // Get the score for this question
                Integer score = answers.get(question.getItemNo().toString());

                if (score != null) {
                    // Add the score to the total score
                    totalScore += score;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle parsing errors or other exceptions
        }

        return totalScore;
    }

}