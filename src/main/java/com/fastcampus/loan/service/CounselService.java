package com.fastcampus.loan.service;

import com.fastcampus.loan.dto.CounselDTO.Request;
import com.fastcampus.loan.dto.CounselDTO.Response;

public interface CounselService {

    Response create(Request request); //상담등록
    
    Response get(Long counselId); //상담조회

    Response update(Long counselId, Request request); //상담수정
    
    void delete(Long counselId); //상담삭제
}
