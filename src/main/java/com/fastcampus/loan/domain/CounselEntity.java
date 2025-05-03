package com.fastcampus.loan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "is_deleted=false")
public class CounselEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long counselId;

    @Column(nullable = false, columnDefinition = "dateTime COMMENT '신청일자'")
    private LocalDateTime appliedAt;

    @Column(nullable = false, columnDefinition = "varchar(12) COMMENT '상담요청자'")
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(23) COMMENT '전화번호'")
    private String cellPhone;

    @Column(nullable = false, columnDefinition = "varchar(50) DEFAULT NULL COMMENT '상담 요청자 이메일'")
    private String email;

    @Column(nullable = false, columnDefinition = "text DEFAULT NULL COMMENT '상담메모'")
    private String memo;

    @Column(nullable = false, columnDefinition = "varchar(50) DEFAULT NULL COMMENT '주소'")
    private String address;

    @Column(nullable = false, columnDefinition = "varchar(50) DEFAULT NULL COMMENT '상세주소'")
    private String addressDetail;

    @Column(nullable = false, columnDefinition = "varchar(5) DEFAULT NULL COMMENT '우편번호'")
    private String zipCode;
}
