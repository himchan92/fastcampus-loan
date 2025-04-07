package com.fastcampus.loan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO.Request;
import com.fastcampus.loan.dto.CounselDTO.Response;
import com.fastcampus.loan.repository.CounselRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CounselServiceTest {

    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void 새로운_상담에대한_요청응답_정상테스트() {
        Counsel entity = Counsel.builder()
            .name("Member Jung")
            .cellPhone("010-1111-2222")
            .email("abc@def.g")
            .memo("저는 대출희망합니다. 연락바랍니다.")
            .zipCode("12345")
            .address("서울 양천구 목동")
            .addressDetail("101동 101호")
            .build();

        Request request = Request.builder()
            .name("Member Jung")
            .cellPhone("010-1111-2222")
            .email("abc@def.g")
            .memo("저는 대출희망합니다. 연락바랍니다.")
            .zipCode("12345")
            .address("서울 양천구 목동")
            .addressDetail("101동 101호")
            .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }
}
