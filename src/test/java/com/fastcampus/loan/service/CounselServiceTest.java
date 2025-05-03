package com.fastcampus.loan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO.Request;
import com.fastcampus.loan.dto.CounselDTO.Response;
import com.fastcampus.loan.repository.CounselRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class CounselServiceTest {
    //테스트코드 어노테이션 아래 개념 공부하자..
    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewCounselEntity_When_RequestCounsel() {
        Counsel entity = Counsel.builder()
            .name("Member Jung")
            .cellPhone("010-2222-3333")
            .email("test@test.com")
            .memo("저는 대출 희망합니다.")
            .zipCode("12345")
            .address("서울 어딘구 어딘동")
            .addressDetail("101동 1004호")
            .build();

        Request request = Request.builder()
            .name("Member Jung")
            .cellPhone("010-2222-3333")
            .email("test@test.com")
            .memo("저는 대출 희망합니다.")
            .zipCode("12345")
            .address("서울 어딘구 어딘동")
            .addressDetail("101동 1004호")
            .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }
}