package com.fastcampus.loan.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CounselDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Request {

        private String name;

        private String cellPhone;

        private String email;

        private String memo;
        
        private String address;

        private String addressDetail;

        private String zipCode;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    //응답하는 엔티티를 변환작업이 필요하니 설정
    @Builder
    @Getter
    @Setter
    public static class Response {

        private Long counselId;

        private String name;

        private String cellPhone;

        private String email;

        private String memo;

        private String address;

        private String addressDetail;

        private String zipCode;

        private LocalDateTime appliedAt;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }

}
