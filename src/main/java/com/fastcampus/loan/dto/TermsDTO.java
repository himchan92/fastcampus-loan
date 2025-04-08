package com.fastcampus.loan.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TermsDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Request {

        private String name;

        private String termsDetailUrl;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {
        private Long termsId;

        private String name;

        private String termsDetailUrl;

        //데이터 적재시점 기록
        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}
