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
@RequiredArgsConstructor
public class CounselServiceImpl implements CounselService {

    private final CounselRepository counselRepository;

    private final ModelMapper modelMapper; //응답 결과 DTO 쉽게 변환 지원 (개별 공부 필요)

    @Override
    public Response create(Request request) {
        Counsel counsel = modelMapper.map(request, Counsel.class); //요청받은 DTO <-> Counsel 엔티티 매핑
        counsel.setAppliedAt(LocalDateTime.now());

        //JPA 저장
        Counsel created = counselRepository.save(counsel);

        //응답결과 Response 매핑 하여 반환
        return modelMapper.map(created, Response.class);
    }

    @Override
    public Response get(Long counselId) {
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        return modelMapper.map(counsel, Response.class);
    }

    @Override
    public Response update(Long counselId, Request request) {
        // 수정 전 대상조회되는지 체크
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        //JPA 변경감지 위해 요청값 setter 설정
        counsel.setName(request.getName());
        counsel.setCellPhone(request.getEmail());
        counsel.setMemo(request.getMemo());
        counsel.setAddress(request.getAddress());
        counsel.setAddressDetail(request.getAddressDetail());
        counsel.setZipCode(request.getZipCode());
        counselRepository.save(counsel);

        return modelMapper.map(counsel, Response.class);
    }
}
