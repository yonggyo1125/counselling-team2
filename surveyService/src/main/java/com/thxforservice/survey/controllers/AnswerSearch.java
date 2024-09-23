package com.thxforservice.survey.controllers;

import com.thxforservice.global.CommonSearch;
import lombok.Data;

import java.util.List;

@Data
public class AnswerSearch extends CommonSearch {
    private List<String> email; // 답안 작성한 회원 이메일
}
