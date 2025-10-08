package com.fastcampus.loan.domain;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@Where(clause = "is_deleted=false")
public class Counsel extends BaseEntity { //기본날짜정보담은 엔티티 상속

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counselId;

    @Column(columnDefinition = "datetime COMMENT '신청일자'")
    private LocalDateTime appliedAt;

    @Column(columnDefinition = "varchar(12) COMMENT '상담요청자'")
    private String name;

    @Column(columnDefinition = "varchar(23) COMMENT '전화번호'")
    private String cellPhone;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '상담 요청자 이메일'")
    private String email;

    @Column(columnDefinition = "text DEFAULT NULL COMMENT '상담 메모'")
    private String memo;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '주소'")
    private String address;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '상세 주소'")
    private String addressDetail;

    @Column(columnDefinition = "varchar(5) DEFAULT NULL COMMENT '우편번호'")
    private String zipcode;

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}