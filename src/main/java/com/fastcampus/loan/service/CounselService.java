package com.fastcampus.loan.service;

import com.fastcampus.loan.dto.CounselDTO.Request;
import com.fastcampus.loan.dto.CounselDTO.Response;

public interface CounselService {

    //등록
    Response create(Request request);

    //조회
    Response get(Long counselId);

    //수정
    Response update(Long counselId, Request request);
    
    //삭제
    void delete(Long counselId);
}
