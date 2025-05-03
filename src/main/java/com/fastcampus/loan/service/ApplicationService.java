package com.fastcampus.loan.service;

import com.fastcampus.loan.dto.CounselDTO.Request;
import com.fastcampus.loan.dto.CounselDTO.Response;

public interface ApplicationService {

    Response create(Request request);

    Response get(Long applicationId);
}
