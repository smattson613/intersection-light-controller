package dev.mattson.intersectionlightcontroller;

import dev.mattson.intersectionlightcontroller.dtos.IntersectionConfigDTO;
import dev.mattson.intersectionlightcontroller.dtos.IntersectionConfigDTOMapper;
import dev.mattson.intersectionlightcontroller.entities.Intersection;
import dev.mattson.intersectionlightcontroller.entities.LightState;
import dev.mattson.intersectionlightcontroller.repositories.IntersectionRepository;
import dev.mattson.intersectionlightcontroller.services.IntersectionServiceImpl;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IntersectionServiceTests {

    @Mock
    private IntersectionRepository intersectionRepository;

    @Mock
    private IntersectionConfigDTOMapper intersectionConfigDTOMapper;

    @InjectMocks
    private IntersectionServiceImpl intersectionService;

    @Test
    void createIntersection_ValidFields_Saved() {
        //given
        Intersection intersection = Intersection.builder()
                .intersectionId(1)
                .NSLight(LightState.RED)
                .EWLight(LightState.GREEN)
                .power(false)
                .NSLightRed(50)
                .NSLightYellow(5)
                .NSLightGreen(30)
                .EWLightRed(50)
                .EWLightYellow(5)
                .EWLightGreen(30).build();
        //when
        when(intersectionRepository.save(Mockito.any(Intersection.class))).thenReturn(intersection);

        Intersection savedIntersection = intersectionService.createIntersection(intersection);
        //then
        Assertions.assertThat(savedIntersection).isNotNull();
        Assertions.assertThat(savedIntersection.isPower()).isEqualTo(false);
    }

    @Test
    void getIntersectionDTOConfigById_IdExists_IntersectionDTOReturned() {
        //given
        Intersection intersection = Intersection.builder()
                .intersectionId(1)
                .NSLight(LightState.RED)
                .EWLight(LightState.GREEN)
                .power(false)
                .NSLightRed(50)
                .NSLightYellow(5)
                .NSLightGreen(30)
                .EWLightRed(50)
                .EWLightYellow(5)
                .EWLightGreen(30).build();
        IntersectionConfigDTO intersectionConfigDTO = IntersectionConfigDTO.builder()
                .id(1)
                .NSLightRed(50)
                .NSLightYellow(5)
                .NSLightGreen(30)
                .EWLightGreen(50)
                .EWLightYellow(5)
                .EWLightGreen(30).build();
        //when
        when(intersectionRepository.findById(1)).thenReturn(Optional.ofNullable(intersection));
        when(intersectionConfigDTOMapper.apply(Mockito.any(Intersection.class))).thenReturn(intersectionConfigDTO);

        Optional<IntersectionConfigDTO> foundIntersectionConfigDTO = intersectionService.getIntersectionConfigDTOById(1);
        //then
        Assertions.assertThat(foundIntersectionConfigDTO).isNotNull();
        Assertions.assertThat(foundIntersectionConfigDTO.get().NSLightRed()).isEqualTo(50);
    }

    @Test
    void startIntersectionById_IdExists_PowerTrue() {
        //given
        Intersection intersection = Intersection.builder()
                .intersectionId(1)
                .NSLight(LightState.RED)
                .EWLight(LightState.GREEN)
                .power(false)
                .NSLightRed(50)
                .NSLightYellow(5)
                .NSLightGreen(30)
                .EWLightRed(50)
                .EWLightYellow(5)
                .EWLightGreen(30).build();
        //when
        when(intersectionRepository.findById(1)).thenReturn(Optional.ofNullable(intersection));
        when(intersectionRepository.save(Mockito.any(Intersection.class))).thenReturn(intersection);

        intersectionService.startIntersectionById(1);
        //then
        Assertions.assertThat(intersection).isNotNull();
        Assertions.assertThat(intersection.isPower()).isTrue();
    }

    @Test
    void stopIntersectionById_IdExists_PowerFalse() {
        //given
        Intersection intersection = Intersection.builder()
                .intersectionId(1)
                .NSLight(LightState.RED)
                .EWLight(LightState.GREEN)
                .power(true)
                .NSLightRed(50)
                .NSLightYellow(5)
                .NSLightGreen(30)
                .EWLightRed(50)
                .EWLightYellow(5)
                .EWLightGreen(30).build();
        //when
        when(intersectionRepository.findById(1)).thenReturn(Optional.ofNullable(intersection));
        when(intersectionRepository.save(Mockito.any(Intersection.class))).thenReturn(intersection);

        intersectionService.stopIntersectionById(1);
        //then
        Assertions.assertThat(intersection).isNotNull();
        Assertions.assertThat(intersection.isPower()).isFalse();
    }

    @Test
    void toggleIntersectionById_IdExists_PowerToggled() {
        //given
        Intersection intersection = Intersection.builder()
                .intersectionId(1)
                .NSLight(LightState.RED)
                .EWLight(LightState.GREEN)
                .power(false)
                .NSLightRed(50)
                .NSLightYellow(5)
                .NSLightGreen(30)
                .EWLightRed(50)
                .EWLightYellow(5)
                .EWLightGreen(30).build();
        //when
        when(intersectionRepository.findById(1)).thenReturn(Optional.ofNullable(intersection));
        when(intersectionRepository.save(Mockito.any(Intersection.class))).thenReturn(intersection);

        intersectionService.toggleIntersectionPowerById(1);
        //then
        Assertions.assertThat(intersection).isNotNull();
        Assertions.assertThat(intersection.isPower()).isTrue();
    }

    @Test
    void setIntersectionConfigById_IdExists_NewConfigSaved() {
        //given
        Intersection intersection = Intersection.builder()
                .intersectionId(1)
                .NSLight(LightState.RED)
                .EWLight(LightState.GREEN)
                .power(false)
                .NSLightRed(50)
                .NSLightYellow(5)
                .NSLightGreen(30)
                .EWLightRed(50)
                .EWLightYellow(5)
                .EWLightGreen(30).build();
        int[] newConfig = {45, 10, 25, 45, 10 ,25};
        //when
        when(intersectionRepository.findById(1)).thenReturn(Optional.ofNullable(intersection));
        when(intersectionRepository.save(Mockito.any(Intersection.class))).thenReturn(intersection);

        intersectionService.setIntersectionConfigById(1, newConfig);
        //then
        Assertions.assertThat(intersection).isNotNull();
        Assertions.assertThat(intersection.getNSLightRed()).isEqualTo(45);
    }
}
