package com.thxforservice.survey.services.answer;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Pagination;
import com.thxforservice.global.Utils;
import com.thxforservice.global.constants.DeleteStatus;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.survey.controllers.SurveySearch;
import com.thxforservice.survey.entities.QSurveyResult;

import com.thxforservice.survey.entities.SurveyResult;
import com.thxforservice.survey.repositories.SurveyInfoRepository;
import com.thxforservice.survey.repositories.SurveyResultRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnswerInfoService {

    private final SurveyResultRepository surveyResultRepository;
    private final JPAQueryFactory queryFactory;
    private final SurveyInfoRepository surveyInfoRepository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;
    private final Utils utils;

    // 한개 설문지결과 조회
    public SurveyResult get(Long prgrsNo) {

        return surveyResultRepository.findById(prgrsNo)
                .orElseThrow(() -> new IllegalArgumentException("설문 결과를 찾을 수 없습니다."));
    }

    // 여러 설문결과 목록 조회
    public ListData<SurveyResult> getList(SurveySearch search, DeleteStatus status, SurveyResult prgrsNo) {

        Long srvyNo = search.getSrvyNo(); // 설문번호
        List<Long> srvyNos = search.getSrvyNos(); // 여러 설문번호

        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();

        int offset = (page - 1) * limit;

        // 삭제가 되지 않은 설문결과 목록이 기본 값
        status = Objects.requireNonNullElse(status, DeleteStatus.UNDELETED);

        String sopt = search.getSopt();
        String skey = search.getSkey();

        /* 검색 처리 S */
        QSurveyResult surveyResult = QSurveyResult.surveyResult;
        BooleanBuilder andBuilder = new BooleanBuilder();

        // 삭제, 미삭제 설문 조회 처리
        if (status != DeleteStatus.ALL) {
            if (status == DeleteStatus.UNDELETED) {
                andBuilder.and(surveyResult.deletedAt.isNull()); // 미삭된
            } else {
                andBuilder.and(surveyResult.deletedAt.isNotNull()); // 삭제된
            }
        }

//        if (srvyNo != null && StringUtils.hasText(srvyNo.trim())) { // 게시판별 조회
//            srvyNos = List.of(srvyNo);
//        }
//
//        if (srvyNos != null && !srvyNo.isEmpty()) { // 게시판 여러개 조회
//            andBuilder.and(surveyResult.surveyInfo.srvyNo.in(srvyNos));
//        }
//
//        // 분류 검색 처리
//        if (categories != null && !categories.isEmpty()) {
//            andBuilder.and(surveyResult.category.in(categories));
//        }

        /**
         * 조건 검색 처리
         */
        sopt = sopt != null && StringUtils.hasText(sopt.trim()) ? sopt.trim() : "ALL";
        if (skey != null && StringUtils.hasText(skey.trim())) {
            skey = skey.trim();
            BooleanExpression condition = null;

            BooleanBuilder orBuilder = new BooleanBuilder();
        }
            /* 검색 처리 E */

            /* 정렬 처리 S */

            String sort = search.getSort();

            PathBuilder<SurveyResult> pathBuilder = new PathBuilder<>(SurveyResult.class, "SurveyResult");
            OrderSpecifier orderSpecifier = null;
            Order order = Order.DESC;
            if (sort != null && StringUtils.hasText(sort.trim())) {
                // 정렬항목_방향   예) viewCount_DESC -> 조회수가 많은 순으로 정렬
                String[] _sort = sort.split("_");
                if (_sort[1].toUpperCase().equals("ASC")) {
                    order = Order.ASC;
                }

                orderSpecifier = new OrderSpecifier(order, pathBuilder.get(_sort[0]));
            }

            List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

            if (orderSpecifier != null) {
                orderSpecifiers.add(orderSpecifier);
            }
            orderSpecifiers.add(surveyResult.createdAt.desc());

            /* 목록 조회 처리 S */
            List<SurveyResult> items = queryFactory
                    .selectFrom(surveyResult)
                    .leftJoin(surveyResult.surveyInfo)
                    .fetchJoin()
                    .where(andBuilder)
                    .orderBy(orderSpecifiers.toArray(OrderSpecifier[]::new))
                    .offset(offset)
                    .limit(limit)
                    .fetch();

            /* 목록 조회 처리 E */

            // 전체  갯수
            long total = surveyResultRepository.count(andBuilder);

            // 페이징 처리
//       int ranges = utils.isMobile() ? board.getPageCountMobile() : board.getPageCountPc();
            Pagination pagination = new Pagination(page, (int) total, limit, request);
            return new ListData<>(items, pagination);
    }
}