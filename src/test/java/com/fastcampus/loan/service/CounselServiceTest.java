package com.fastcampus.loan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.fastcampus.loan.domain.BaseEntity;
import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO.Request;
import com.fastcampus.loan.dto.CounselDTO.Response;
import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import com.fastcampus.loan.repository.CounselRepository;
import java.util.Optional;
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

    @Test
    void 존재하는_상담아이디가있으면_정상조회테스트() {
        Long findId = 1L;

        Counsel entity = Counsel.builder()
            .counselId(1L)
            .build();

        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity)); //없는경우 Optional null 체크

        Response actual = counselService.get(findId);

        assertThat(actual.getCounselId()).isSameAs(findId);
    }

    @Test
    void 존재하지않는_상담아이디로_요청시에러발생테스트() {
        Long findId = 2L;

        when(counselRepository.findById(findId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));

        assertThrows(BaseException.class, () -> counselService.get(findId));
    }

    @Test
    void 존재하는_상담요청응답내용_정상수정테스트() {
        Long findId = 1L;

        Counsel entity = Counsel.builder()
            .counselId(1L)
            .name("Member Jung")
            .build();

        Request request = Request.builder()
            .name("Member Kim")
            .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);
        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.update(findId, request);

        assertThat(actual.getCounselId()).isSameAs(findId);
        assertThat(actual.getName()).isSameAs(request.getName());
    }

    @Test
    void 요청시_상담등록내역_정상삭제테스트() {
        Long targetId = 1L;

        Counsel entity = Counsel.builder()
            .counselId(1L)
            .build();

        //Mock 테스트용 데이터 생성
        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);
        when(counselRepository.findById(targetId)).thenReturn(Optional.ofNullable(entity));

        counselService.delete(targetId);

        assertThat(entity.getIsDeleted()).isSameAs(true);
    }
}
