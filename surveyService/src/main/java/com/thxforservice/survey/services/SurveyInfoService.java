package com.thxforservice.survey.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Utils;
import com.thxforservice.global.constants.DeleteStatus;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.survey.controllers.SurveyInfoResponse;
import com.thxforservice.survey.controllers.SurveySearch;
import com.thxforservice.survey.entities.QSurveyInfo;
import com.thxforservice.survey.entities.SurveyInfo;
import com.thxforservice.survey.entities.SurveyQuestion;
import com.thxforservice.survey.exceptions.SurveyInfoNotFoundException;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import com.thxforservice.survey.repositories.SurveyQuestionRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class SurveyInfoService {
    private final JPAQueryFactory queryFactory;
    private final SurveyInfoRepository surveyInfoRepository;
//km private final SurveyConfigInfoService sConfigInfoService;
private final SurveyQuestionRepository sQuestionRepository;
    private final HttpServletRequest request;
    private final ModelMapper modelMapper;
    private final MemberUtil memberUtil;
    private final Utils utils;

    // 한개 조회
    public SurveyInfoResponse get(Long srvyNo) {
        // SurveyInfo 정보 조회
        SurveyInfo surveyInfo = surveyInfoRepository.findById(srvyNo)
                .orElseThrow(() -> new IllegalArgumentException("Survey Info not found"));

        // 해당 srvyNo에 대한 SurveyQuestion 리스트 조회
        List<SurveyQuestion> questions = sQuestionRepository.findBySurveyInfo_SrvyNo(srvyNo);

        // itemNo 리스트 생성
        List<Long> itemNos = questions.stream()
                .map(SurveyQuestion::getItemNo)
                .collect(Collectors.toList());

        // SurveyInfoResponse 빌더를 사용하여 결과 생성
        return SurveyInfoResponse.builder()
                .surveyInfo(surveyInfo)
                .itemNos(itemNos)
                .build();
    }

    // 목록 조회
    public ListData<SurveyInfo> getList(SurveySearch search, DeleteStatus status) {
        Long srvyNo = search.getSrvyNo();
        List<Long> srvyNos= search.getSrvyNos();

        // 삭제가 되지 않은 게시글 목록이 기본 값
        status = Objects.requireNonNullElse(status, DeleteStatus.UNDELETED);

        /* 검색 처리 S */
        QSurveyInfo surveyInfo = QSurveyInfo.surveyInfo;
        BooleanBuilder andBuilder = new BooleanBuilder();


        // 삭제, 미삭제 게시글 조회 처리
        if (status != DeleteStatus.ALL) {
            if (status == DeleteStatus.UNDELETED) {
                andBuilder.and(surveyInfo.deletedAt.isNull()); // 미삭된 게시글
            } else {
                andBuilder.and(surveyInfo.deletedAt.isNotNull()); // 삭제된 게시글
            }
        }
        return null;

    }
}