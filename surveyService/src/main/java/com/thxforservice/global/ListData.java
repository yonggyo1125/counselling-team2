package com.thxforservice.global;

import com.thxforservice.survey.entities.SurveyResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListData<T> {
    private List<T> data;
    private List<T> items; // 목록 데이터
    private Pagination pagination;
    private int totalCount;

    // 명시적으로 생성자 추가
    public ListData(List<T> data) {
        this.data = data;
    }

    public ListData(List<T> items, Pagination pagination) {
        this.items = items;
        this.pagination = pagination;
    }

    public ListData(List<T> items, int totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }

}