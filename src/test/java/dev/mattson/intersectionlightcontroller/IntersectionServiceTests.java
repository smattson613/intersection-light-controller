package dev.mattson.intersectionlightcontroller;

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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IntersectionServiceTests {

    @Mock
    private IntersectionRepository intersectionRepository;

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
    }

}
