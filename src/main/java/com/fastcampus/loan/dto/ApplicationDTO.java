package com.fastcampus.loan.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ApplicationDTO implements Serializable {

    /*
        API 요청 처리 구조 셋팅 (postman 요청부분)
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter @Setter
    public static class Request {

        private String name;

        private String cellPhone;

        private String email;

        private BigDecimal hopeAmount;
    }

    /*
        API 응답 처리 구조 설정 (postman 응답 결과부분)
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter @Setter
    public static class Response {

        private Long applicationId;

        private String name;

        private String cellPhone;

        private String email;

        private BigDecimal hopeAmount;

        private LocalDateTime appliedAt;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}
