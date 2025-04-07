package com.fastcampus.loan.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

import com.fastcampus.loan.domain.Application;
import com.fastcampus.loan.dto.ApplicationDTO.Request;
import com.fastcampus.loan.dto.ApplicationDTO.Response;
import com.fastcampus.loan.repository.ApplicationRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationServiceTest {

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void 신규대출신청_정상반환_테스트() {
        Application entity = Application.builder()
            .name("Member Jung")
            .cellPhone("010-1111-2222")
            .email("mail@abcd.efg")
            .hopeAmount(BigDecimal.valueOf(50000000))
            .build();

        Request request = Request.builder()
            .name("Member Jung")
            .cellPhone("010-1111-2222")
            .email("mail@abcd.efg")
            .hopeAmount(BigDecimal.valueOf(50000000))
            .build();

        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(entity);

        Response actual = applicationService.create(request);

        assertThat(actual.getHopeAmount()).isSameAs(entity.getHopeAmount());
        assertThat(actual.getName()).isSameAs(entity.getName());
    }

    @Test
    void 존재하는_대출신청_정상조회_테스트() {
        Long findId = 1L;

        Application entity = Application.builder()
            .applicationId(1L)
            .build();

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.get(findId);

        assertThat(actual.getApplicationId()).isSameAs(entity.getApplicationId());
    }

    @Test
    void 존재하는_신청정보_업데이트_정상테스트() {
        Long findId = 1L;

        Application entity = Application.builder()
            .applicationId(1L)
            .hopeAmount(BigDecimal.valueOf(50000000))
            .build();

        Request request = Request.builder()
            .hopeAmount(BigDecimal.valueOf(5000000))
            .build();

        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(entity);
        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.update(findId, request);

        assertThat(actual.getHopeAmount()).isSameAs(request.getHopeAmount());
    }
}