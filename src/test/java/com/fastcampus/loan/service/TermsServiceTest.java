package com.fastcampus.loan.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

import com.fastcampus.loan.domain.Terms;
import com.fastcampus.loan.dto.TermsDTO.Request;
import com.fastcampus.loan.dto.TermsDTO.Response;
import com.fastcampus.loan.repository.TermsRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TermsServiceTest {

    @InjectMocks
    TermsServiceImpl termsService;

    @Mock
    private TermsRepository termsRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void 새로운_약관정보_요청_정상테스트() {
        Terms entity = Terms.builder()
            .name("대출 이용 약관")
            .termsDetailUrl("https://abc-storage.acc/djlksadjsladsa")
            .build();

        Request request = Request.builder()
            .name("대출 이용 약관")
            .termsDetailUrl("https://abc-storage.acc/djlksadjsladsa")
            .build();

        when(termsRepository.save(ArgumentMatchers.any(Terms.class))).thenReturn(entity);

        Response actual = termsService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
        assertThat(actual.getTermsDetailUrl()).isSameAs(entity.getTermsDetailUrl());
    }

    @Test
    void 모든약관_조회_정상테스트() {
        Terms entityA = Terms.builder()
            .name("대출이용약관 1")
            .termsDetailUrl("https://test.asdsadsa/dasadasd")
            .build();

        Terms entityB = Terms.builder()
            .name("대출이용약관 2")
            .termsDetailUrl("https://test.asdsadsa/dasadasd")
            .build();

        List<Terms> list = new ArrayList<>(Arrays.asList(entityA, entityB));
        when(termsRepository.findAll()).thenReturn(Arrays.asList(entityA, entityB));
        List<Response> actual = termsService.getAll();

        assertThat(actual.size()).isSameAs(list.size());
    }
}