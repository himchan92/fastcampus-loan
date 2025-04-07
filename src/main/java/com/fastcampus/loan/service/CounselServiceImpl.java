package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO.Request;
import com.fastcampus.loan.dto.CounselDTO.Response;
import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import com.fastcampus.loan.repository.CounselRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //final 붙은 객체 DI 지원
public class CounselServiceImpl implements CounselService {

    private final CounselRepository counselRepository;

    //DTO <-> Entity 쉽게 변환 처리 지원1
    private final ModelMapper modelMapper;

    //상담등록
    //응답, 요청 별도 DTO 만들어 엔티티 외부노출 방지, 필요한 내용만 JSON 반환
    @Override
    public Response create(Request request) {
        Counsel counsel = modelMapper.map(request, Counsel.class);//Counsel 엔티티 클래스 내용을 request 에 매핑
        counsel.setAppliedAt(LocalDateTime.now());
        Counsel created = counselRepository.save(counsel); //상담등록 JPA 저장

        //Response 응답에 상담등록한 데이터 매핑
        return modelMapper.map(created, Response.class);
    }

    @Override
    public Response get(Long counselId) {
        //조회 요청 하되 없으면 사용자정의 예외 처리
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        return modelMapper.map(counsel, Response.class);
    }
}
